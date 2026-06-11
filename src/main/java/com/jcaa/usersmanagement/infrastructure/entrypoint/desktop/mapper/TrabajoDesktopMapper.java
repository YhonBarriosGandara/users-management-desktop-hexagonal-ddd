package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.CantidadTrabajosPorAutorDto;
import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorAutorQuery;
import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorPalabraClaveQuery;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo.CantidadTrabajosPorAutorResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo.TrabajoResponse;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TrabajoDesktopMapper {

  public static TrabajoResponse toResponse(final TrabajoModel model) {
    final Long id = model.getId() != null ? model.getId().value() : null;
    return new TrabajoResponse(
        id,
        model.getTitulo(),
        model.getResumen(),
        model.getEstado(),
        model.getSesionId(),
        model.getPonenteId());
  }

  public static List<TrabajoResponse> toResponseList(final List<TrabajoModel> models) {
    return models.stream().map(TrabajoDesktopMapper::toResponse).toList();
  }

  public static CantidadTrabajosPorAutorResponse toCantidadResponse(
      final CantidadTrabajosPorAutorDto dto) {
    return new CantidadTrabajosPorAutorResponse(
        dto.autorId(),
        dto.nombre(),
        dto.apellido(),
        dto.totalTrabajos());
  }

  public static List<CantidadTrabajosPorAutorResponse> toCantidadResponseList(
      final List<CantidadTrabajosPorAutorDto> dtos) {
    return dtos.stream().map(TrabajoDesktopMapper::toCantidadResponse).toList();
  }

  public static ListTrabajosPorAutorQuery toListTrabajosPorAutorQuery(final Long autorId) {
    return new ListTrabajosPorAutorQuery(autorId);
  }

  public static ListTrabajosPorPalabraClaveQuery toListTrabajosPorPalabraClaveQuery(
      final String palabraClave) {
    return new ListTrabajosPorPalabraClaveQuery(palabraClave);
  }
}
