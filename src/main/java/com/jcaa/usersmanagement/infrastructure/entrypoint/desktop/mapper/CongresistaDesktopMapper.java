package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.congresista.CreateCongresistaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.congresista.DeleteCongresistaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.congresista.UpdateCongresistaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.congresista.GetCongresistaByIdQuery;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CongresistaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CreateCongresistaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.UpdateCongresistaRequest;
import java.util.List;

public final class CongresistaDesktopMapper {

  private CongresistaDesktopMapper() {}

  public static CreateCongresistaCommand toCreateCommand(final CreateCongresistaRequest request) {
    return new CreateCongresistaCommand(
        request.nombre(),
        request.apellido(),
        request.institucion(),
        request.email(),
        request.telefono(),
        request.esMiembroComite());
  }

  public static UpdateCongresistaCommand toUpdateCommand(final UpdateCongresistaRequest request) {
    return new UpdateCongresistaCommand(
        request.id(),
        request.nombre(),
        request.apellido(),
        request.institucion(),
        request.email(),
        request.telefono(),
        request.esMiembroComite());
  }

  public static DeleteCongresistaCommand toDeleteCommand(final Long id) {
    return new DeleteCongresistaCommand(id);
  }

  public static GetCongresistaByIdQuery toGetByIdQuery(final Long id) {
    return new GetCongresistaByIdQuery(id);
  }

  public static CongresistaResponse toResponse(final CongresistaModel congresista) {
    final Long id = congresista.getId() != null ? congresista.getId().value() : null;
    return new CongresistaResponse(
        id,
        congresista.getNombre(),
        congresista.getApellido(),
        congresista.getInstitucion(),
        congresista.getEmail().value(),
        congresista.getTelefono(),
        congresista.getEsMiembroComite());
  }

  public static List<CongresistaResponse> toResponseList(final List<CongresistaModel> congresistas) {
    return congresistas.stream().map(CongresistaDesktopMapper::toResponse).toList();
  }
}
