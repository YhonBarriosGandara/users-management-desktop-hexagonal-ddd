package com.jcaa.usersmanagement.application.port.in.congresista;

import com.jcaa.usersmanagement.application.service.dto.command.congresista.UpdateCongresistaCommand;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateCongresistaUseCase {
  CongresistaModel execute(@NotNull @Valid UpdateCongresistaCommand command);
}
