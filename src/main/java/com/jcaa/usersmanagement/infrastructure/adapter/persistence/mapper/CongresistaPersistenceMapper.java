package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaEmail;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.CongresistaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.congresista.CongresistaEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CongresistaPersistenceMapper {

  public CongresistaPersistenceDto fromModelToDto(final CongresistaModel congresista) {
    final Long id = congresista.getId() != null ? congresista.getId().value() : null;
    return new CongresistaPersistenceDto(
        id,
        congresista.getNombre(),
        congresista.getApellido(),
        congresista.getInstitucion(),
        congresista.getEmail().value(),
        congresista.getTelefono(),
        congresista.getEsMiembroComite());
  }

  public CongresistaEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    final long id = resultSet.getLong("id");
    return new CongresistaEntity(
        resultSet.wasNull() ? null : id,
        resultSet.getString("nombre"),
        resultSet.getString("apellido"),
        resultSet.getString("institucion"),
        resultSet.getString("email"),
        resultSet.getString("telefono"),
        resultSet.getBoolean("es_miembro_comite"));
  }

  public CongresistaModel fromEntityToModel(final CongresistaEntity entity) {
    final CongresistaId id = entity.id() != null ? new CongresistaId(entity.id()) : null;
    return new CongresistaModel(
        id,
        entity.nombre(),
        entity.apellido(),
        entity.institucion(),
        new CongresistaEmail(entity.email()),
        entity.telefono(),
        entity.esMiembroComite());
  }

  public CongresistaModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<CongresistaModel> fromResultSetToModelList(final ResultSet resultSet)
      throws SQLException {
    final List<CongresistaModel> congresistas = new ArrayList<>();
    while (resultSet.next()) {
      congresistas.add(fromResultSetToModel(resultSet));
    }
    return congresistas;
  }
}
