package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record CongresistaPersistenceDto(
    Long id,
    String nombre,
    String apellido,
    String institucion,
    String email,
    String telefono,
    Boolean esMiembroComite) {}
