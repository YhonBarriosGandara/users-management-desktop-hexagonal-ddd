package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.trabajo;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.trabajo.TrabajoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo.TrabajoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListTrabajosPorPalabraClaveHandler implements OperationHandler {

  private final TrabajoController trabajoController;
  private final ConsoleIO console;
  private final TrabajoResponsePrinter printer;

  @Override
  public void handle() {
    final String palabraClave = console.readRequired("Palabra clave: ");
    final List<TrabajoResponse> trabajos =
        trabajoController.findTrabajosByPalabraClave(palabraClave);
    printer.printList(trabajos);
  }
}
