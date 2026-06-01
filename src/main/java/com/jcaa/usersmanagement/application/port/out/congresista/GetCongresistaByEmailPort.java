package com.jcaa.usersmanagement.application.port.out.congresista;

import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaEmail;
import java.util.Optional;

public interface GetCongresistaByEmailPort {
  Optional<CongresistaModel> getByEmail(CongresistaEmail email);
}
