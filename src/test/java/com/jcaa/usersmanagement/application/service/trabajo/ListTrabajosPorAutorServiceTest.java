package com.jcaa.usersmanagement.application.service.trabajo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPorAutorPort;
import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorAutorQuery;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import com.jcaa.usersmanagement.domain.valueobject.TrabajoId;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para ListTrabajosPorAutorService.
 *
 * <p>Cubre: validación del query y flujo feliz.
 */
@DisplayName("ListTrabajosPorAutorService")
@ExtendWith(MockitoExtension.class)
class ListTrabajosPorAutorServiceTest {

  @Mock private ListTrabajosPorAutorPort listTrabajosPorAutorPort;

  private ListTrabajosPorAutorService service;

  @BeforeEach
  void setUp() {
    try (final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
      service = new ListTrabajosPorAutorService(listTrabajosPorAutorPort, validatorFactory.getValidator());
    }
  }

  // ── flujo feliz

  @Test
  @DisplayName("execute() retorna los trabajos del autor cuando el query es válido")
  void shouldReturnTrabajosByAutorIdWhenQueryIsValid() {
    // Arrange
    final ListTrabajosPorAutorQuery query = new ListTrabajosPorAutorQuery(42L);
    final TrabajoModel trabajo = buildTrabajo(1L, "Patrones DDD", "Agregados y repositorios");
    when(listTrabajosPorAutorPort.getByAutorId(42L)).thenReturn(List.of(trabajo));

    // Act
    final List<TrabajoModel> result = service.execute(query);

    // Assert
    assertAll(
        "listar trabajos por autor",
        () -> assertEquals(1, result.size(), "debe retornar exactamente un trabajo"),
        () -> assertSame(trabajo, result.get(0), "debe ser el mismo objeto del puerto"));
  }

  // ── validación del query

  @Test
  @DisplayName("execute() lanza ConstraintViolationException cuando autorId es nulo")
  void shouldThrowWhenAutorIdIsNull() {
    // Arrange
    final ListTrabajosPorAutorQuery query = new ListTrabajosPorAutorQuery(null);

    // Act & Assert
    assertThrows(ConstraintViolationException.class, () -> service.execute(query));
    verifyNoInteractions(listTrabajosPorAutorPort);
  }

  // ── helpers

  private static TrabajoModel buildTrabajo(final long id, final String titulo, final String resumen) {
    return TrabajoModel.create(new TrabajoId(id), titulo, resumen, "REVISADO", 101L, 202L);
  }
}
