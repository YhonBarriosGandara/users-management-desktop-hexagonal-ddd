package com.jcaa.usersmanagement.application.port.in.trabajo;

import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorPalabraClaveQuery;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface ListTrabajosPorPalabraClaveUseCase {
  List<TrabajoModel> execute(@NotNull @Valid ListTrabajosPorPalabraClaveQuery query);
}
