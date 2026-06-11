package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.TrabajoId;
import lombok.Value;

@Value
public class TrabajoModel {

  TrabajoId id;
  String titulo;
  String resumen;
  String estado;
  Long sesionId;
  Long ponenteId;

  public static TrabajoModel create(
      final TrabajoId id,
      final String titulo,
      final String resumen,
      final String estado,
      final Long sesionId,
      final Long ponenteId) {
    return new TrabajoModel(id, titulo, resumen, estado, sesionId, ponenteId);
  }
}
