package com.jcaa.usersmanagement.application.service.congresista;

import com.jcaa.usersmanagement.application.port.in.congresista.GetCongresistaByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.congresista.GetCongresistaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.congresista.GetCongresistaByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.CongresistaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CongresistaNotFoundException;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetCongresistaByIdService implements GetCongresistaByIdUseCase {

  private final GetCongresistaByIdPort getCongresistaByIdPort;
  private final Validator validator;

  @Override
  public CongresistaModel execute(final GetCongresistaByIdQuery query) {
    validateQuery(query);

    final CongresistaId congresistaId =
        CongresistaApplicationMapper.fromGetCongresistaByIdQueryToId(query);
    return getCongresistaByIdPort
        .getById(congresistaId)
        .orElseThrow(
            () -> CongresistaNotFoundException.becauseIdWasNotFound(congresistaId.value()));
  }

  private void validateQuery(final GetCongresistaByIdQuery query) {
    final Set<ConstraintViolation<GetCongresistaByIdQuery>> violations =
        validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
