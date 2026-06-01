package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.congresista;

import com.jcaa.usersmanagement.application.port.in.congresista.CreateCongresistaUseCase;
import com.jcaa.usersmanagement.application.port.in.congresista.DeleteCongresistaUseCase;
import com.jcaa.usersmanagement.application.port.in.congresista.GetCongresistaByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.congresista.ListCongresistasUseCase;
import com.jcaa.usersmanagement.application.port.in.congresista.UpdateCongresistaUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CongresistaResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.CreateCongresistaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.congresista.UpdateCongresistaRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.CongresistaDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CongresistaController {

  private final CreateCongresistaUseCase createCongresistaUseCase;
  private final UpdateCongresistaUseCase updateCongresistaUseCase;
  private final DeleteCongresistaUseCase deleteCongresistaUseCase;
  private final GetCongresistaByIdUseCase getCongresistaByIdUseCase;
  private final ListCongresistasUseCase listCongresistasUseCase;

  public List<CongresistaResponse> listAllCongresistas() {
    final var congresistas = listCongresistasUseCase.execute();
    return CongresistaDesktopMapper.toResponseList(congresistas);
  }

  public CongresistaResponse findCongresistaById(final Long id) {
    final var query = CongresistaDesktopMapper.toGetByIdQuery(id);
    final var congresista = getCongresistaByIdUseCase.execute(query);
    return CongresistaDesktopMapper.toResponse(congresista);
  }

  public CongresistaResponse createCongresista(final CreateCongresistaRequest request) {
    final var command = CongresistaDesktopMapper.toCreateCommand(request);
    final var congresista = createCongresistaUseCase.execute(command);
    return CongresistaDesktopMapper.toResponse(congresista);
  }

  public CongresistaResponse updateCongresista(final UpdateCongresistaRequest request) {
    final var command = CongresistaDesktopMapper.toUpdateCommand(request);
    final var congresista = updateCongresistaUseCase.execute(command);
    return CongresistaDesktopMapper.toResponse(congresista);
  }

  public void deleteCongresista(final Long id) {
    final var command = CongresistaDesktopMapper.toDeleteCommand(id);
    deleteCongresistaUseCase.execute(command);
  }
}
