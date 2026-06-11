package com.jcaa.usersmanagement.application.service.trabajo;

import com.jcaa.usersmanagement.application.port.in.trabajo.ListTrabajosPorAutoresConTelefonoUseCase;
import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPorAutoresConTelefonoPort;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListTrabajosPorAutoresConTelefonoService implements ListTrabajosPorAutoresConTelefonoUseCase {

  private final ListTrabajosPorAutoresConTelefonoPort listTrabajosPorAutoresConTelefonoPort;

  @Override
  public List<TrabajoModel> execute() {
    return listTrabajosPorAutoresConTelefonoPort.getByAutoresConTelefono();
  }
}
