package com.jcaa.usersmanagement.application.port.in.trabajo;

import com.jcaa.usersmanagement.application.service.dto.CantidadTrabajosPorAutorDto;
import java.util.List;

public interface CountTrabajosPorAutorUseCase {
  List<CantidadTrabajosPorAutorDto> execute();
}
