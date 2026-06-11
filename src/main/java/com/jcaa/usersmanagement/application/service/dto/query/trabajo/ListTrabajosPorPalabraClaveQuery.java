package com.jcaa.usersmanagement.application.service.dto.query.trabajo;

import jakarta.validation.constraints.NotBlank;

public record ListTrabajosPorPalabraClaveQuery(
    @NotBlank(message = "palabraClave no debe estar vacia")
    String palabraClave) {

}
