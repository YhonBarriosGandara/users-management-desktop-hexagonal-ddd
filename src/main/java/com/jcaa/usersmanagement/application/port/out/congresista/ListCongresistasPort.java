package com.jcaa.usersmanagement.application.port.out.congresista;

import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import java.util.List;

public interface ListCongresistasPort {
  List<CongresistaModel> getAll();
}
