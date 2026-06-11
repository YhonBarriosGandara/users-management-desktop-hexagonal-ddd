package com.jcaa.usersmanagement.application.service.trabajo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPort;
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
 * Tests para ListTrabajosService.
 *
 * <p>Cubre: lista con trabajos y lista vacía.
 */
@DisplayName("ListTrabajosService")
@ExtendWith(MockitoExtension.class)
class ListTrabajosServiceTest {

  @Mock private ListTrabajosPort listTrabajosPort;

  private ListTrabajosService service;

  @BeforeEach
  void setUp() {
    service = new ListTrabajosService(listTrabajosPort);
  }

  // ── lista con trabajos

  @Test
  @DisplayName("execute() retorna la lista de trabajos del puerto")
  void shouldReturnTrabajosFromPort() {
    // Arrange
    final TrabajoModel trabajo = buildTrabajo(1L, "Arquitectura Hexagonal", "DDD en Java");
    when(listTrabajosPort.getAll()).thenReturn(List.of(trabajo));

    // Act
    final List<TrabajoModel> result = service.execute();

    // Assert
    assertAll(
        "listar trabajos con un elemento",
        () -> assertEquals(1, result.size(), "debe retornar exactamente un trabajo"),
        () -> assertSame(trabajo, result.get(0), "debe ser el mismo objeto del puerto"));
  }

  // ── lista vacía

  @Test
  @DisplayName("execute() retorna lista vacía cuando no hay trabajos")
  void shouldReturnEmptyListWhenNoTrabajos() {
    // Arrange
    when(listTrabajosPort.getAll()).thenReturn(List.of());

    // Act
    final List<TrabajoModel> result = service.execute();

    // Assert
    assertTrue(result.isEmpty(), "debe retornar lista vacía");
  }

  // ── helpers

  private static TrabajoModel buildTrabajo(final long id, final String titulo, final String resumen) {
    return TrabajoModel.create(new TrabajoId(id), titulo, resumen, "ACEPTADO", 100L, 200L);
  }
}
