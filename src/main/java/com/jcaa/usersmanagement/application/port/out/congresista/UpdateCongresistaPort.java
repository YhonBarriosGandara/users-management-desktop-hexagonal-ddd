package com.jcaa.usersmanagement.application.port.out.congresista;

import com.jcaa.usersmanagement.domain.model.CongresistaModel;

public interface UpdateCongresistaPort {
  CongresistaModel update(CongresistaModel congresista);
}
