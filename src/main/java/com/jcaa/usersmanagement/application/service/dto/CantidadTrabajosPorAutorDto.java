package com.jcaa.usersmanagement.application.service.dto;

public record CantidadTrabajosPorAutorDto(
    Long autorId,
    String nombre,
    String apellido,
    Long totalTrabajos) {

}
