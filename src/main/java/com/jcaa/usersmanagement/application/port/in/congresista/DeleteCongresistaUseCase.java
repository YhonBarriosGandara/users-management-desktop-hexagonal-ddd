package com.jcaa.usersmanagement.application.port.in.congresista;

import com.jcaa.usersmanagement.application.service.dto.command.congresista.DeleteCongresistaCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteCongresistaUseCase {
  void execute(@NotNull @Valid DeleteCongresistaCommand command);
}
