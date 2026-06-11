package com.jcaa.usersmanagement.application.service.trabajo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.trabajo.CountTrabajosPorAutorPort;
import com.jcaa.usersmanagement.application.service.dto.CantidadTrabajosPorAutorDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para CountTrabajosPorAutorService.
 *
 * <p>Cubre: conteo con datos y lista vacía.
 */
@DisplayName("CountTrabajosPorAutorService")
@ExtendWith(MockitoExtension.class)
class CountTrabajosPorAutorServiceTest {

  @Mock private CountTrabajosPorAutorPort countTrabajosPorAutorPort;

  private CountTrabajosPorAutorService service;

  @BeforeEach
  void setUp() {
    service = new CountTrabajosPorAutorService(countTrabajosPorAutorPort);
  }

  // ── conteo con datos

  @Test
  @DisplayName("execute() retorna la lista de cantidad por autor del puerto")
  void shouldReturnCountByAutorFromPort() {
    // Arrange
    final CantidadTrabajosPorAutorDto dto =
        new CantidadTrabajosPorAutorDto(1L, "John", "Arrieta", 5L);
    when(countTrabajosPorAutorPort.countByAutor()).thenReturn(List.of(dto));

    // Act
    final List<CantidadTrabajosPorAutorDto> result = service.execute();

    // Assert
    assertAll(
        "contar trabajos por autor",
        () -> assertEquals(1, result.size(), "debe retornar exactamente un registro"),
        () -> assertSame(dto, result.get(0), "debe ser el mismo objeto del puerto"),
        () -> assertEquals(5L, result.get(0).totalTrabajos(), "total de trabajos debe coincidir"));
  }

  // ── lista vacía

  @Test
  @DisplayName("execute() retorna lista vacía cuando no hay trabajos")
  void shouldReturnEmptyListWhenNoCounts() {
    // Arrange
    when(countTrabajosPorAutorPort.countByAutor()).thenReturn(List.of());

    // Act
    final List<CantidadTrabajosPorAutorDto> result = service.execute();

    // Assert
    assertTrue(result.isEmpty(), "debe retornar lista vacía");
  }
}
