package com.jcaa.usersmanagement.application.service.congresista;

import com.jcaa.usersmanagement.application.port.in.congresista.UpdateCongresistaUseCase;
import com.jcaa.usersmanagement.application.port.out.congresista.GetCongresistaByEmailPort;
import com.jcaa.usersmanagement.application.port.out.congresista.GetCongresistaByIdPort;
import com.jcaa.usersmanagement.application.port.out.congresista.UpdateCongresistaPort;
import com.jcaa.usersmanagement.application.service.dto.command.congresista.UpdateCongresistaCommand;
import com.jcaa.usersmanagement.application.service.mapper.CongresistaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CongresistaAlreadyExistsException;
import com.jcaa.usersmanagement.domain.exception.CongresistaNotFoundException;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaEmail;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateCongresistaService implements UpdateCongresistaUseCase {

  private final UpdateCongresistaPort updateCongresistaPort;
  private final GetCongresistaByIdPort getCongresistaByIdPort;
  private final GetCongresistaByEmailPort getCongresistaByEmailPort;
  private final Validator validator;

  @Override
  public CongresistaModel execute(final UpdateCongresistaCommand command) {
    validateCommand(command);

    final CongresistaId congresistaId = new CongresistaId(command.id());
    final CongresistaModel current = findExistingCongresistaOrFail(congresistaId);
    final CongresistaEmail newEmail = new CongresistaEmail(command.email());

    ensureEmailIsNotTakenByAnotherCongresista(newEmail, congresistaId);

    final CongresistaModel congresistaToUpdate =
        CongresistaApplicationMapper.fromUpdateCommandToModel(command);
    return updateCongresistaPort.update(congresistaToUpdate);
  }

  private void validateCommand(final UpdateCongresistaCommand command) {
    final Set<ConstraintViolation<UpdateCongresistaCommand>> violations =
        validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private CongresistaModel findExistingCongresistaOrFail(final CongresistaId congresistaId) {
    return getCongresistaByIdPort
        .getById(congresistaId)
        .orElseThrow(
            () -> CongresistaNotFoundException.becauseIdWasNotFound(congresistaId.value()));
  }

  private void ensureEmailIsNotTakenByAnotherCongresista(
      final CongresistaEmail newEmail, final CongresistaId ownerId) {
    getCongresistaByEmailPort
        .getByEmail(newEmail)
        .ifPresent(
            found -> {
              if (!found.getId().equals(ownerId)) {
                throw CongresistaAlreadyExistsException.becauseEmailAlreadyExists(
                    newEmail.value());
              }
            });
  }
}
