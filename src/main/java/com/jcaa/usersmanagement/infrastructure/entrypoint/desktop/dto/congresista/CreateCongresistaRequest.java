package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista;

public record CreateCongresistaRequest(
    String nombre,
    String apellido,
    String institucion,
    String email,
    String telefono,
    Boolean esMiembroComite) {}
