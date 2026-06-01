package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.congresista;

import com.jcaa.usersmanagement.domain.exception.CongresistaNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.congresista.CongresistaController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteCongresistaHandler implements OperationHandler {

  private final CongresistaController congresistaController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final Long id = (long) console.readInt("ID del congresista a eliminar: ");
    try {
      congresistaController.deleteCongresista(id);
      console.println("  Congresista eliminado exitosamente.");
    } catch (final CongresistaNotFoundException exception) {
      console.println("  No encontrado: " + exception.getMessage());
    }
  }
}
