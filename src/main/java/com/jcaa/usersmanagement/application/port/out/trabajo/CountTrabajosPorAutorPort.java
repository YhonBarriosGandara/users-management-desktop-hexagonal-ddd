package com.jcaa.usersmanagement.application.port.out.trabajo;

import com.jcaa.usersmanagement.application.service.dto.CantidadTrabajosPorAutorDto;
import java.util.List;

public interface CountTrabajosPorAutorPort {
  List<CantidadTrabajosPorAutorDto> countByAutor();
}
