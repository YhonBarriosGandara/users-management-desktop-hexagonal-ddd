package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.trabajo;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo.CantidadTrabajosPorAutorResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo.TrabajoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class TrabajoResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-20s : %s%n";

  private final ConsoleIO console;

  public void print(final TrabajoResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",        response.id());
    console.printf(ROW_FORMAT, "Titulo",    response.titulo());
    console.printf(ROW_FORMAT, "Resumen",   response.resumen());
    console.printf(ROW_FORMAT, "Estado",    response.estado());
    console.printf(ROW_FORMAT, "Sesion ID", response.sesionId());
    console.printf(ROW_FORMAT, "Ponente ID", response.ponenteId());
    console.println(SEPARATOR);
  }

  public void printList(final List<TrabajoResponse> trabajos) {
    if (trabajos.isEmpty()) {
      console.println("  No se encontraron trabajos.");
      return;
    }
    console.printf("%n  Total: %d trabajo(s)%n", trabajos.size());
    trabajos.forEach(this::print);
  }

  public void printCantidadList(final List<CantidadTrabajosPorAutorResponse> resultados) {
    if (resultados.isEmpty()) {
      console.println("  No se encontraron resultados.");
      return;
    }
    console.printf("%n  Total: %d autor(es)%n", resultados.size());
    console.println(SEPARATOR);
    for (final CantidadTrabajosPorAutorResponse r : resultados) {
      console.printf("  %s %s: %d trabajo(s)%n", r.nombre(), r.apellido(), r.totalTrabajos());
    }
    console.println(SEPARATOR);
  }
}
