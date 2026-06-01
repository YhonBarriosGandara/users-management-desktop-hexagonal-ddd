package com.jcaa.usersmanagement.application.port.in.congresista;

import com.jcaa.usersmanagement.application.service.dto.query.congresista.GetCongresistaByIdQuery;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetCongresistaByIdUseCase {
  CongresistaModel execute(@NotNull @Valid GetCongresistaByIdQuery query);
}
