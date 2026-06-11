package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.trabajo.CountTrabajosPorAutorPort;
import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPorAutoresConTelefonoPort;
import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPorAutorPort;
import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPorPalabraClavePort;
import com.jcaa.usersmanagement.application.port.out.trabajo.ListTrabajosPort;
import com.jcaa.usersmanagement.application.service.dto.CantidadTrabajosPorAutorDto;
import com.jcaa.usersmanagement.domain.model.TrabajoModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.TrabajoPersistenceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class TrabajoRepositoryMySQL
    implements ListTrabajosPort,
        ListTrabajosPorAutorPort,
        CountTrabajosPorAutorPort,
        ListTrabajosPorPalabraClavePort,
        ListTrabajosPorAutoresConTelefonoPort {

  private static final String SQL_SELECT_ALL =
      "SELECT id, titulo, resumen, estado, sesion_id, ponente_id "
          + "FROM trabajo ORDER BY titulo ASC";

  private static final String SQL_SELECT_BY_AUTOR_ID =
      "SELECT t.id, t.titulo, t.resumen, t.estado, t.sesion_id, t.ponente_id "
          + "FROM trabajo t INNER JOIN trabajo_autor ta ON t.id = ta.trabajo_id "
          + "WHERE ta.congresista_id = ? ORDER BY t.titulo ASC";

  private static final String SQL_COUNT_BY_AUTOR =
      "SELECT c.id AS autor_id, c.nombre, c.apellido, COUNT(ta.trabajo_id) AS total_trabajos "
          + "FROM congresista c LEFT JOIN trabajo_autor ta ON c.id = ta.congresista_id "
          + "GROUP BY c.id, c.nombre, c.apellido ORDER BY c.apellido ASC";

  private static final String SQL_SELECT_BY_PALABRA_CLAVE =
      "SELECT id, titulo, resumen, estado, sesion_id, ponente_id "
          + "FROM trabajo WHERE titulo LIKE ? OR resumen LIKE ? ORDER BY titulo ASC";

  private static final String SQL_SELECT_BY_AUTORES_CON_TELEFONO =
      "SELECT t.id, t.titulo, t.resumen, t.estado, t.sesion_id, t.ponente_id "
          + "FROM trabajo t INNER JOIN trabajo_autor ta ON t.id = ta.trabajo_id "
          + "INNER JOIN congresista c ON ta.congresista_id = c.id "
          + "WHERE c.telefono IS NOT NULL AND c.telefono <> '' ORDER BY t.titulo ASC";

  private final Connection connection;

  @Override
  public List<TrabajoModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return TrabajoPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseQueryFailed("getAll", exception);
    }
  }

  @Override
  public List<TrabajoModel> getByAutorId(final Long autorId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_AUTOR_ID)) {
      statement.setLong(1, autorId);
      final ResultSet resultSet = statement.executeQuery();
      return TrabajoPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseQueryFailed("getByAutorId", exception);
    }
  }

  @Override
  public List<CantidadTrabajosPorAutorDto> countByAutor() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_COUNT_BY_AUTOR)) {
      final ResultSet resultSet = statement.executeQuery();
      final List<CantidadTrabajosPorAutorDto> resultados = new ArrayList<>();
      while (resultSet.next()) {
        resultados.add(
            new CantidadTrabajosPorAutorDto(
                resultSet.getLong("autor_id"),
                resultSet.getString("nombre"),
                resultSet.getString("apellido"),
                resultSet.getLong("total_trabajos")));
      }
      return resultados;
    } catch (final SQLException exception) {
      throw PersistenceException.becauseQueryFailed("countByAutor", exception);
    }
  }

  @Override
  public List<TrabajoModel> getByPalabraClave(final String palabraClave) {
    try (final PreparedStatement statement =
        connection.prepareStatement(SQL_SELECT_BY_PALABRA_CLAVE)) {
      final String pattern = "%" + palabraClave + "%";
      statement.setString(1, pattern);
      statement.setString(2, pattern);
      final ResultSet resultSet = statement.executeQuery();
      return TrabajoPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseQueryFailed("getByPalabraClave", exception);
    }
  }

  @Override
  public List<TrabajoModel> getByAutoresConTelefono() {
    try (final PreparedStatement statement =
        connection.prepareStatement(SQL_SELECT_BY_AUTORES_CON_TELEFONO)) {
      final ResultSet resultSet = statement.executeQuery();
      return TrabajoPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseQueryFailed("getByAutoresConTelefono", exception);
    }
  }
}
