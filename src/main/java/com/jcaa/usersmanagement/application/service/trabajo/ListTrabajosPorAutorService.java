package com.jcaa.usersmanagement.application.service.trabajo;

import com.jcaa.usersmanagement.application.port.in.trabajo.ListTrabajosPorAutorUseCase;
import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPorAutorPort;
import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorAutorQuery;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListTrabajosPorAutorService implements ListTrabajosPorAutorUseCase {

  private final ListTrabajosPorAutorPort listTrabajosPorAutorPort;
  private final Validator validator;

  @Override
  public List<TrabajoModel> execute(final ListTrabajosPorAutorQuery query) {
    validateQuery(query);
    return listTrabajosPorAutorPort.getByAutorId(query.autorId());
  }

  private void validateQuery(final ListTrabajosPorAutorQuery query) {
    final Set<ConstraintViolation<ListTrabajosPorAutorQuery>> violations =
        validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
