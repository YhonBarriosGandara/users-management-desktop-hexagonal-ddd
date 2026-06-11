package com.jcaa.usersmanagement.application.port.out.trabajo;

import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import java.util.List;

public interface ListTrabajosPorPalabraClavePort {
  List<TrabajoModel> getByPalabraClave(String palabraClave);
}
