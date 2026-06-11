package com.jcaa.usersmanagement.application.port.in.trabajo;

import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import java.util.List;

public interface ListTrabajosPorAutoresConTelefonoUseCase {
  List<TrabajoModel> execute();
}
