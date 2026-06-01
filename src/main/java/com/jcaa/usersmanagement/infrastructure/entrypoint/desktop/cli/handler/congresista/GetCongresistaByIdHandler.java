package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.congresista;

import com.jcaa.usersmanagement.domain.exception.CongresistaNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.congresista.CongresistaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CongresistaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetCongresistaByIdHandler implements OperationHandler {

  private final CongresistaController congresistaController;
  private final ConsoleIO console;
  private final CongresistaResponsePrinter printer;

  @Override
  public void handle() {
    final Long id = (long) console.readInt("ID del congresista: ");
    try {
      final CongresistaResponse congresista = congresistaController.findCongresistaById(id);
      printer.print(congresista);
    } catch (final CongresistaNotFoundException exception) {
      console.println("  No encontrado: " + exception.getMessage());
    }
  }
}
