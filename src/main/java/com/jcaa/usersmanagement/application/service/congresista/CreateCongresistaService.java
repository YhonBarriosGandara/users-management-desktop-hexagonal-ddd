package com.jcaa.usersmanagement.application.service.congresista;

import com.jcaa.usersmanagement.application.port.in.congresista.CreateCongresistaUseCase;
import com.jcaa.usersmanagement.application.port.out.congresista.GetCongresistaByEmailPort;
import com.jcaa.usersmanagement.application.port.out.congresista.SaveCongresistaPort;
import com.jcaa.usersmanagement.application.service.dto.command.congresista.CreateCongresistaCommand;
import com.jcaa.usersmanagement.application.service.mapper.CongresistaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CongresistaAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class CreateCongresistaService implements CreateCongresistaUseCase {

  private final SaveCongresistaPort saveCongresistaPort;
  private final GetCongresistaByEmailPort getCongresistaByEmailPort;
  private final Validator validator;

  @Override
  public CongresistaModel execute(final CreateCongresistaCommand command) {
    validateCommand(command);

    final CongresistaEmail email = new CongresistaEmail(command.email());
    ensureEmailIsNotTaken(email);

    final CongresistaModel congresistaToSave =
        CongresistaApplicationMapper.fromCreateCommandToModel(command);
    return saveCongresistaPort.save(congresistaToSave);
  }

  private void validateCommand(final CreateCongresistaCommand command) {
    final Set<ConstraintViolation<CreateCongresistaCommand>> violations =
        validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureEmailIsNotTaken(final CongresistaEmail email) {
    getCongresistaByEmailPort
        .getByEmail(email)
        .ifPresent(
            ignored -> {
              throw CongresistaAlreadyExistsException.becauseEmailAlreadyExists(email.value());
            });
  }
}
