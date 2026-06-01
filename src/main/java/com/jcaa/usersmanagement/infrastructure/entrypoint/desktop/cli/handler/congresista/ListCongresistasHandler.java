package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.congresista;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.congresista.CongresistaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CongresistaResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListCongresistasHandler implements OperationHandler {

  private final CongresistaController congresistaController;
  private final CongresistaResponsePrinter printer;

  @Override
  public void handle() {
    final List<CongresistaResponse> congresistas = congresistaController.listAllCongresistas();
    printer.printList(congresistas);
  }
}
