package com.jcaa.usersmanagement.application.port.in.congresista;

import com.jcaa.usersmanagement.application.service.dto.command.congresista.CreateCongresistaCommand;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateCongresistaUseCase {
  CongresistaModel execute(@NotNull @Valid CreateCongresistaCommand command);
}
