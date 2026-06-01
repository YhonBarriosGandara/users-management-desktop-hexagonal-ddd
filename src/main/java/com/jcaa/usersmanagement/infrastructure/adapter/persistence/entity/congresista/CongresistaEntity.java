package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.congresista;

public record CongresistaEntity(
    Long id,
    String nombre,
    String apellido,
    String institucion,
    String email,
    String telefono,
    Boolean esMiembroComite) {}
