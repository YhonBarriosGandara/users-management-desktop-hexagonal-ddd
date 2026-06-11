package com.jcaa.usersmanagement.application.service.trabajo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPorPalabraClavePort;
import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorPalabraClaveQuery;
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
 * Tests para ListTrabajosPorPalabraClaveService.
 *
 * <p>Cubre: validación del query y flujo feliz.
 */
@DisplayName("ListTrabajosPorPalabraClaveService")
@ExtendWith(MockitoExtension.class)
class ListTrabajosPorPalabraClaveServiceTest {

  @Mock private ListTrabajosPorPalabraClavePort listTrabajosPorPalabraClavePort;

  private ListTrabajosPorPalabraClaveService service;

  @BeforeEach
  void setUp() {
    try (final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
      service =
          new ListTrabajosPorPalabraClaveService(
              listTrabajosPorPalabraClavePort, validatorFactory.getValidator());
    }
  }

  // ── flujo feliz

  @Test
  @DisplayName("execute() retorna los trabajos que coinciden con la palabra clave")
  void shouldReturnTrabajosByPalabraClaveWhenQueryIsValid() {
    // Arrange
    final ListTrabajosPorPalabraClaveQuery query = new ListTrabajosPorPalabraClaveQuery("hexagonal");
    final TrabajoModel trabajo = buildTrabajo(1L, "Arquitectura Hexagonal", "Patrón hexagonal");
    when(listTrabajosPorPalabraClavePort.getByPalabraClave("hexagonal")).thenReturn(List.of(trabajo));

    // Act
    final List<TrabajoModel> result = service.execute(query);

    // Assert
    assertAll(
        "listar trabajos por palabra clave",
        () -> assertEquals(1, result.size(), "debe retornar exactamente un trabajo"),
        () -> assertSame(trabajo, result.get(0), "debe ser el mismo objeto del puerto"));
  }

  // ── validación del query

  @Test
  @DisplayName("execute() lanza ConstraintViolationException cuando palabraClave está en blanco")
  void shouldThrowWhenPalabraClaveIsBlank() {
    // Arrange
    final ListTrabajosPorPalabraClaveQuery query = new ListTrabajosPorPalabraClaveQuery("");

    // Act & Assert
    assertThrows(ConstraintViolationException.class, () -> service.execute(query));
    verifyNoInteractions(listTrabajosPorPalabraClavePort);
  }

  // ── helpers

  private static TrabajoModel buildTrabajo(final long id, final String titulo, final String resumen) {
    return TrabajoModel.create(new TrabajoId(id), titulo, resumen, "PUBLICADO", 50L, 75L);
  }
}
