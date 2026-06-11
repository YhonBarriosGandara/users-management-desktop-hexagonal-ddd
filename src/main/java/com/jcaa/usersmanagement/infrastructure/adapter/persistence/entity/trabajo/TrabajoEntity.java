package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.trabajo;

public record TrabajoEntity(
    Long id,
    String titulo,
    String resumen,
    String estado,
    Long sesionId,
    Long ponenteId) {}
