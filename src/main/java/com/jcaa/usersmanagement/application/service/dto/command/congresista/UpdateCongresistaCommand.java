package com.jcaa.usersmanagement.application.service.dto.command.congresista;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCongresistaCommand(
    @NotNull(message = "id no debe ser nulo") Long id,
    @NotBlank(message = "nombre no debe estar vacio")
        @Size(min = 3, message = "nombre debe tener al menos 3 caracteres")
        String nombre,
    @NotBlank(message = "apellido no debe estar vacio")
        @Size(min = 3, message = "apellido debe tener al menos 3 caracteres")
        String apellido,
    @NotBlank(message = "institucion no debe estar vacia") String institucion,
    @NotBlank(message = "email no debe estar vacio")
        @Email(message = "email debe ser una direccion valida")
        String email,
    String telefono,
    @NotNull(message = "esMiembroComite no debe ser nulo") Boolean esMiembroComite)
{

}
