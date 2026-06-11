package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.trabajo;

import com.jcaa.usersmanagement.application.port.in.trabajo.CountTrabajosPorAutorUseCase;
import com.jcaa.usersmanagement.application.port.in.trabajo.ListTrabajosPorAutoresConTelefonoUseCase;
import com.jcaa.usersmanagement.application.port.in.trabajo.ListTrabajosPorAutorUseCase;
import com.jcaa.usersmanagement.application.port.in.trabajo.ListTrabajosPorPalabraClaveUseCase;
import com.jcaa.usersmanagement.application.port.in.trabajo.ListTrabajosUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo.CantidadTrabajosPorAutorResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo.TrabajoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.TrabajoDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class TrabajoController {

  private final ListTrabajosUseCase listTrabajosUseCase;
  private final ListTrabajosPorAutorUseCase listTrabajosPorAutorUseCase;
  private final CountTrabajosPorAutorUseCase countTrabajosPorAutorUseCase;
  private final ListTrabajosPorPalabraClaveUseCase listTrabajosPorPalabraClaveUseCase;
  private final ListTrabajosPorAutoresConTelefonoUseCase listTrabajosPorAutoresConTelefonoUseCase;

  public List<TrabajoResponse> listAllTrabajos() {
    final var trabajos = listTrabajosUseCase.execute();
    return TrabajoDesktopMapper.toResponseList(trabajos);
  }

  public List<TrabajoResponse> findTrabajosByAutorId(final Long autorId) {
    final var query = TrabajoDesktopMapper.toListTrabajosPorAutorQuery(autorId);
    final var trabajos = listTrabajosPorAutorUseCase.execute(query);
    return TrabajoDesktopMapper.toResponseList(trabajos);
  }

  public List<CantidadTrabajosPorAutorResponse> countTrabajosByAutor() {
    final var resultados = countTrabajosPorAutorUseCase.execute();
    return TrabajoDesktopMapper.toCantidadResponseList(resultados);
  }

  public List<TrabajoResponse> findTrabajosByPalabraClave(final String palabraClave) {
    final var query = TrabajoDesktopMapper.toListTrabajosPorPalabraClaveQuery(palabraClave);
    final var trabajos = listTrabajosPorPalabraClaveUseCase.execute(query);
    return TrabajoDesktopMapper.toResponseList(trabajos);
  }

  public List<TrabajoResponse> listTrabajosByAutoresConTelefono() {
    final var trabajos = listTrabajosPorAutoresConTelefonoUseCase.execute();
    return TrabajoDesktopMapper.toResponseList(trabajos);
  }
}
