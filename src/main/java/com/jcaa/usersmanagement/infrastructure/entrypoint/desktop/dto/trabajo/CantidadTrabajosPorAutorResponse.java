package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo;

public record CantidadTrabajosPorAutorResponse(
    Long autorId,
    String nombre,
    String apellido,
    Long totalTrabajos) {}
