package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuOption {

  LIST_USERS(1, "List all users"),
  FIND_USER(2, "Find user by ID"),
  CREATE_USER(3, "Create user"),
  UPDATE_USER(4, "Update user"),
  DELETE_USER(5, "Delete user"),
  LOGIN(6, "Login"),
  LIST_CONGRESISTAS(7, "Listar congresistas"),
  FIND_CONGRESISTA(8, "Buscar congresista por ID"),
  CREATE_CONGRESISTA(9, "Crear congresista"),
  UPDATE_CONGRESISTA(10, "Actualizar congresista"),
  DELETE_CONGRESISTA(11, "Eliminar congresista"),
  LIST_TRABAJOS(12, "Listar trabajos enviados"),
  LIST_TRABAJOS_POR_AUTOR(13, "Listar trabajos por autor"),
  COUNT_TRABAJOS_POR_AUTOR(14, "Cantidad de trabajos por autor"),
  LIST_TRABAJOS_POR_PALABRA_CLAVE(15, "Buscar trabajos por palabra clave"),
  LIST_TRABAJOS_POR_AUTORES_CON_TELEFONO(16, "Trabajos de autores con telefono"),
  EXIT(0, "Exit");

  private final int number;
  private final String description;

  public static Optional<MenuOption> fromNumber(final int number) {
    for (final MenuOption option : values()) {
      if (option.number == number) {
        return Optional.of(option);
      }
    }
    return Optional.empty();
  }
}

