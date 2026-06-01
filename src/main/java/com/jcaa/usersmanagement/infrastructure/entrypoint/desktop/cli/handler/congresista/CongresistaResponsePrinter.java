package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.congresista;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CongresistaResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CongresistaResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-20s : %s%n";

  private final ConsoleIO console;

  public void print(final CongresistaResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",               response.id());
    console.printf(ROW_FORMAT, "Nombre",           response.nombre());
    console.printf(ROW_FORMAT, "Apellido",         response.apellido());
    console.printf(ROW_FORMAT, "Institucion",      response.institucion());
    console.printf(ROW_FORMAT, "Email",            response.email());
    console.printf(ROW_FORMAT, "Telefono",         response.telefono());
    console.printf(ROW_FORMAT, "Es miembro comite", response.esMiembroComite() ? "Si" : "No");
    console.println(SEPARATOR);
  }

  public void printList(final List<CongresistaResponse> congresistas) {
    if (congresistas.isEmpty()) {
      console.println("  No se encontraron congresistas.");
      return;
    }
    console.printf("%n  Total: %d congresista(s)%n", congresistas.size());
    congresistas.forEach(this::print);
  }
}
