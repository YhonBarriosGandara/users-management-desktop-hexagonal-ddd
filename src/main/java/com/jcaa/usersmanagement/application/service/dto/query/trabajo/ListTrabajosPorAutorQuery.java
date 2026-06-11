package com.jcaa.usersmanagement.application.service.dto.query.trabajo;

import jakarta.validation.constraints.NotNull;

public record ListTrabajosPorAutorQuery(
    @NotNull(message = "autorId no debe ser nulo")
    Long autorId) {

}
