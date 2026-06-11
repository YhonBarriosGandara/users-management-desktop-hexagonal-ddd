package com.jcaa.usersmanagement.application.service.trabajo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPorAutoresConTelefonoPort;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import com.jcaa.usersmanagement.domain.valueobject.TrabajoId;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para ListTrabajosPorAutoresConTelefonoService.
 *
 * <p>Cubre: lista con trabajos y lista vacía.
 */
@DisplayName("ListTrabajosPorAutoresConTelefonoService")
@ExtendWith(MockitoExtension.class)
class ListTrabajosPorAutoresConTelefonoServiceTest {

  @Mock private ListTrabajosPorAutoresConTelefonoPort listTrabajosPorAutoresConTelefonoPort;

  private ListTrabajosPorAutoresConTelefonoService service;

  @BeforeEach
  void setUp() {
    service = new ListTrabajosPorAutoresConTelefonoService(listTrabajosPorAutoresConTelefonoPort);
  }

  // ── lista con trabajos

  @Test
  @DisplayName("execute() retorna los trabajos de autores con teléfono")
  void shouldReturnTrabajosFromPort() {
    // Arrange
    final TrabajoModel trabajo = buildTrabajo(1L, "Microservicios con DDD", "Implementación práctica");
    when(listTrabajosPorAutoresConTelefonoPort.getByAutoresConTelefono()).thenReturn(List.of(trabajo));

    // Act
    final List<TrabajoModel> result = service.execute();

    // Assert
    assertAll(
        "listar trabajos de autores con teléfono",
        () -> assertEquals(1, result.size(), "debe retornar exactamente un trabajo"),
        () -> assertSame(trabajo, result.get(0), "debe ser el mismo objeto del puerto"));
  }

  // ── lista vacía

  @Test
  @DisplayName("execute() retorna lista vacía cuando no hay trabajos de autores con teléfono")
  void shouldReturnEmptyListWhenNoTrabajos() {
    // Arrange
    when(listTrabajosPorAutoresConTelefonoPort.getByAutoresConTelefono()).thenReturn(List.of());

    // Act
    final List<TrabajoModel> result = service.execute();

    // Assert
    assertTrue(result.isEmpty(), "debe retornar lista vacía");
  }

  // ── helpers

  private static TrabajoModel buildTrabajo(final long id, final String titulo, final String resumen) {
    return TrabajoModel.create(new TrabajoId(id), titulo, resumen, "ACEPTADO", 300L, 400L);
  }
}
