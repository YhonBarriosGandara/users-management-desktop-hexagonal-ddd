package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo;

public record TrabajoResponse(
    Long id,
    String titulo,
    String resumen,
    String estado,
    Long sesionId,
    Long ponenteId) {}
