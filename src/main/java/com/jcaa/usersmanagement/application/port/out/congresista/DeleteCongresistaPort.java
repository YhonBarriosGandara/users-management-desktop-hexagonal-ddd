package com.jcaa.usersmanagement.application.port.out.congresista;

import com.jcaa.usersmanagement.domain.valueobject.CongresistaId;

public interface DeleteCongresistaPort {
  void delete(CongresistaId congresistaId);
}
