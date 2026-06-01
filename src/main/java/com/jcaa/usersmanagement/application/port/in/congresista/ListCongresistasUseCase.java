package com.jcaa.usersmanagement.application.port.in.congresista;

import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import java.util.List;

public interface ListCongresistasUseCase {
  List<CongresistaModel> execute();
}
