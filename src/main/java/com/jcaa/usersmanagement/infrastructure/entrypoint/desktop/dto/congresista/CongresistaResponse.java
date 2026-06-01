package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista;

public record CongresistaResponse(
    Long id,
    String nombre,
    String apellido,
    String institucion,
    String email,
    String telefono,
    Boolean esMiembroComite) {}
