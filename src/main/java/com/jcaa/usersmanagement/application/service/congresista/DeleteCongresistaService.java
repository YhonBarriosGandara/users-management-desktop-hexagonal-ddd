package com.jcaa.usersmanagement.application.service.congresista;

import com.jcaa.usersmanagement.application.port.in.congresista.DeleteCongresistaUseCase;
import com.jcaa.usersmanagement.application.port.out.congresista.DeleteCongresistaPort;
import com.jcaa.usersmanagement.application.port.out.congresista.GetCongresistaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.congresista.DeleteCongresistaCommand;
import com.jcaa.usersmanagement.application.service.mapper.CongresistaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CongresistaNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteCongresistaService implements DeleteCongresistaUseCase {

  private final DeleteCongresistaPort deleteCongresistaPort;
  private final GetCongresistaByIdPort getCongresistaByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteCongresistaCommand command) {
    validateCommand(command);

    final CongresistaId congresistaId =
        CongresistaApplicationMapper.fromDeleteCommandToId(command);
    ensureCongresistaExists(congresistaId);
    deleteCongresistaPort.delete(congresistaId);
  }

  private void validateCommand(final DeleteCongresistaCommand command) {
    final Set<ConstraintViolation<DeleteCongresistaCommand>> violations =
        validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureCongresistaExists(final CongresistaId congresistaId) {
    getCongresistaByIdPort
        .getById(congresistaId)
        .orElseThrow(
            () -> CongresistaNotFoundException.becauseIdWasNotFound(congresistaId.value()));
  }
}
