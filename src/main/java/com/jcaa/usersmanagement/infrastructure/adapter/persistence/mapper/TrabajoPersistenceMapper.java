package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import com.jcaa.usersmanagement.domain.valueobject.TrabajoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.trabajo.TrabajoEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TrabajoPersistenceMapper {

  public TrabajoEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    final long id = resultSet.getLong("id");
    return new TrabajoEntity(
        resultSet.wasNull() ? null : id,
        resultSet.getString("titulo"),
        resultSet.getString("resumen"),
        resultSet.getString("estado"),
        getLongOrNull(resultSet, "sesion_id"),
        getLongOrNull(resultSet, "ponente_id"));
  }

  public TrabajoModel fromEntityToModel(final TrabajoEntity entity) {
    final TrabajoId id = entity.id() != null ? new TrabajoId(entity.id()) : null;
    return new TrabajoModel(
        id,
        entity.titulo(),
        entity.resumen(),
        entity.estado(),
        entity.sesionId(),
        entity.ponenteId());
  }

  public TrabajoModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<TrabajoModel> fromResultSetToModelList(final ResultSet resultSet)
      throws SQLException {
    final List<TrabajoModel> trabajos = new ArrayList<>();
    while (resultSet.next()) {
      trabajos.add(fromResultSetToModel(resultSet));
    }
    return trabajos;
  }

  private static Long getLongOrNull(final ResultSet resultSet, final String columnLabel)
      throws SQLException {
    final long value = resultSet.getLong(columnLabel);
    return resultSet.wasNull() ? null : value;
  }
}
