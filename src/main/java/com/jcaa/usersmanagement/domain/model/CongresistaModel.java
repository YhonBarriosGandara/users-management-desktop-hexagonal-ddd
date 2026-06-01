package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.CongresistaEmail;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaId;
import lombok.Value;

@Value
public class CongresistaModel {

  CongresistaId id;
  String nombre;
  String apellido;
  String institucion;
  CongresistaEmail email;
  String telefono;
  Boolean esMiembroComite;

  public static CongresistaModel create(
      final CongresistaId id,
      final String nombre,
      final String apellido,
      final String institucion,
      final CongresistaEmail email,
      final String telefono,
      final Boolean esMiembroComite) {
    return new CongresistaModel(id, nombre, apellido, institucion, email, telefono, esMiembroComite);
  }
}
