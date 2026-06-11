package com.jcaa.usersmanagement.application.service.trabajo;

import com.jcaa.usersmanagement.application.port.in.trabajo.CountTrabajosPorAutorUseCase;
import com.jcaa.usersmanagement.application.port.out.trabajo.CountTrabajosPorAutorPort;
import com.jcaa.usersmanagement.application.service.dto.CantidadTrabajosPorAutorDto;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CountTrabajosPorAutorService implements CountTrabajosPorAutorUseCase {

  private final CountTrabajosPorAutorPort countTrabajosPorAutorPort;

  @Override
  public List<CantidadTrabajosPorAutorDto> execute() {
    return countTrabajosPorAutorPort.countByAutor();
  }
}
