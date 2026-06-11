package com.jcaa.usersmanagement.application.service.trabajo;

import com.jcaa.usersmanagement.application.port.in.trabajo.ListTrabajosPorPalabraClaveUseCase;
import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPorPalabraClavePort;
import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorPalabraClaveQuery;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListTrabajosPorPalabraClaveService implements ListTrabajosPorPalabraClaveUseCase {

  private final ListTrabajosPorPalabraClavePort listTrabajosPorPalabraClavePort;
  private final Validator validator;

  @Override
  public List<TrabajoModel> execute(final ListTrabajosPorPalabraClaveQuery query) {
    validateQuery(query);
    return listTrabajosPorPalabraClavePort.getByPalabraClave(query.palabraClave());
  }

  private void validateQuery(final ListTrabajosPorPalabraClaveQuery query) {
    final Set<ConstraintViolation<ListTrabajosPorPalabraClaveQuery>> violations =
        validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
