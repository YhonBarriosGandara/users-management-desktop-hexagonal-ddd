package com.jcaa.usersmanagement.application.port.out.congresista;

import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaId;
import java.util.Optional;

public interface GetCongresistaByIdPort {
  Optional<CongresistaModel> getById(CongresistaId congresistaId);
}
