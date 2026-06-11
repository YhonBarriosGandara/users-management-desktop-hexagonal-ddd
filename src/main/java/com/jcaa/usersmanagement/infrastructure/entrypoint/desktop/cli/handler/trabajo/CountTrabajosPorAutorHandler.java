package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.trabajo;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.trabajo.TrabajoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.trabajo.CantidadTrabajosPorAutorResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CountTrabajosPorAutorHandler implements OperationHandler {

  private final TrabajoController trabajoController;
  private final TrabajoResponsePrinter printer;

  @Override
  public void handle() {
    final List<CantidadTrabajosPorAutorResponse> resultados =
        trabajoController.countTrabajosByAutor();
    printer.printCantidadList(resultados);
  }
}
