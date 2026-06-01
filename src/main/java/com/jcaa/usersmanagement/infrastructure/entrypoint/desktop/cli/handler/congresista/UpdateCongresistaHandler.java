package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.congresista;

import com.jcaa.usersmanagement.domain.exception.CongresistaNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.congresista.CongresistaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CongresistaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.UpdateCongresistaRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateCongresistaHandler implements OperationHandler {

  private final CongresistaController congresistaController;
  private final ConsoleIO console;
  private final CongresistaResponsePrinter printer;

  @Override
  public void handle() {
    final Long id               = (long) console.readInt("ID del congresista                    : ");
    final String nombre         = console.readRequired("Nuevo nombre                         : ");
    final String apellido       = console.readRequired("Nuevo apellido                       : ");
    final String institucion    = console.readRequired("Nueva institucion                    : ");
    final String email          = console.readRequired("Nuevo email                          : ");
    final String telefono       = console.readOptional("Nuevo telefono                       : ");
    final String esMiembroStr   = console.readRequired("Es miembro del comite? (S/N)         : ");
    final Boolean esMiembroComite = esMiembroStr.equalsIgnoreCase("S");

    try {
      final CongresistaResponse updated = congresistaController.updateCongresista(
          new UpdateCongresistaRequest(id, nombre, apellido, institucion, email, telefono, esMiembroComite));
      console.println("\n  Congresista actualizado exitosamente.");
      printer.print(updated);
    } catch (final CongresistaNotFoundException exception) {
      console.println("  No encontrado: " + exception.getMessage());
    }
  }
}
