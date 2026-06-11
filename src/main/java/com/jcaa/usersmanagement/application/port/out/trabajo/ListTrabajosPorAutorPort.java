package com.jcaa.usersmanagement.application.port.out.trabajo;

import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import java.util.List;

public interface ListTrabajosPorAutorPort {
  List<TrabajoModel> getByAutorId(Long autorId);
}
