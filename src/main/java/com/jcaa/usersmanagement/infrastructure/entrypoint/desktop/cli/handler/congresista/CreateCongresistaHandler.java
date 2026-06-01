package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.congresista;

import com.jcaa.usersmanagement.domain.exception.CongresistaAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.congresista.CongresistaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CongresistaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CreateCongresistaRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateCongresistaHandler implements OperationHandler {

  private final CongresistaController congresistaController;
  private final ConsoleIO console;
  private final CongresistaResponsePrinter printer;

  @Override
  public void handle() {
    final String nombre          = console.readRequired("Nombre                          : ");
    final String apellido        = console.readRequired("Apellido                        : ");
    final String institucion     = console.readRequired("Institucion                     : ");
    final String email           = console.readRequired("Email                           : ");
    final String telefono        = console.readOptional("Telefono                        : ");
    final String esMiembroStr    = console.readRequired("Es miembro del comite? (S/N)    : ");
    final Boolean esMiembroComite = esMiembroStr.equalsIgnoreCase("S");

    try {
      final CongresistaResponse created = congresistaController.createCongresista(
          new CreateCongresistaRequest(nombre, apellido, institucion, email, telefono, esMiembroComite));
      console.println("\n  Congresista creado exitosamente.");
      printer.print(created);
    } catch (final CongresistaAlreadyExistsException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
