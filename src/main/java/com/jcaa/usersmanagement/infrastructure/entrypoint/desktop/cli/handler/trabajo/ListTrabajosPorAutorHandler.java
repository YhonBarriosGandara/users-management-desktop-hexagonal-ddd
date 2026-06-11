package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.trabajo;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.trabajo.TrabajoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo.TrabajoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListTrabajosPorAutorHandler implements OperationHandler {

  private final TrabajoController trabajoController;
  private final ConsoleIO console;
  private final TrabajoResponsePrinter printer;

  @Override
  public void handle() {
    final Long autorId = (long) console.readInt("ID del autor (congresista): ");
    final List<TrabajoResponse> trabajos = trabajoController.findTrabajosByAutorId(autorId);
    printer.printList(trabajos);
  }
}
