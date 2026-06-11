package com.jcaa.usersmanagement.application.port.in.trabajo;

import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorAutorQuery;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface ListTrabajosPorAutorUseCase {
  List<TrabajoModel> execute(@NotNull @Valid ListTrabajosPorAutorQuery query);
}
