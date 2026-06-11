package com.jcaa.usersmanagement.application.service.trabajo;

import com.jcaa.usersmanagement.application.port.in.trabajo.ListTrabajosUseCase;
import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPort;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListTrabajosService implements ListTrabajosUseCase {

  private final ListTrabajosPort listTrabajosPort;

  @Override
  public List<TrabajoModel> execute() {
    return listTrabajosPort.getAll();
  }
}
