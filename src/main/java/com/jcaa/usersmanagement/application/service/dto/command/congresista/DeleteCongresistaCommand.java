package com.jcaa.usersmanagement.application.service.dto.command.congresista;

import jakarta.validation.constraints.NotNull;

public record DeleteCongresistaCommand(
    @NotNull(message = "id no debe ser nulo") Long id
) {

}
