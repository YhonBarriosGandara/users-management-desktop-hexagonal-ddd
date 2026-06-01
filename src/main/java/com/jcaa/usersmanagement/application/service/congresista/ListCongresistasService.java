package com.jcaa.usersmanagement.application.service.congresista;

import com.jcaa.usersmanagement.application.port.in.congresista.ListCongresistasUseCase;
import com.jcaa.usersmanagement.application.port.out.congresista.ListCongresistasPort;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListCongresistasService implements ListCongresistasUseCase {

  private final ListCongresistasPort listCongresistasPort;

  @Override
  public List<CongresistaModel> execute() {
    return listCongresistasPort.getAll();
  }
}
