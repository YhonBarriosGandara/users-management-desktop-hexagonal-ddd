package com.jcaa.usersmanagement.application.service.dto.query.congresista;

import jakarta.validation.constraints.NotNull;

public record GetCongresistaByIdQuery(@NotNull(message = "id no debe ser nulo") Long id)
{

}
