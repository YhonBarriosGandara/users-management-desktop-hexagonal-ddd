package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.congresista.DeleteCongresistaPort;
import com.jcaa.usersmanagement.application.port.out.congresista.GetCongresistaByEmailPort;
import com.jcaa.usersmanagement.application.port.out.congresista.GetCongresistaByIdPort;
import com.jcaa.usersmanagement.application.port.out.congresista.ListCongresistasPort;
import com.jcaa.usersmanagement.application.port.out.congresista.SaveCongresistaPort;
import com.jcaa.usersmanagement.application.port.out.congresista.UpdateCongresistaPort;
import com.jcaa.usersmanagement.domain.exception.CongresistaNotFoundException;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaEmail;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.CongresistaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.CongresistaPersistenceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class CongresistaRepositoryMySQL
    implements SaveCongresistaPort,
        UpdateCongresistaPort,
        GetCongresistaByIdPort,
        GetCongresistaByEmailPort,
        ListCongresistasPort,
        DeleteCongresistaPort {

  private static final String SQL_INSERT =
      "INSERT INTO congresista (nombre, apellido, institucion, email, telefono, es_miembro_comite) "
          + "VALUES (?, ?, ?, ?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE congresista SET nombre = ?, apellido = ?, institucion = ?, email = ?, telefono = ?, es_miembro_comite = ? "
          + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, nombre, apellido, institucion, email, telefono, es_miembro_comite "
          + "FROM congresista WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_EMAIL =
      "SELECT id, nombre, apellido, institucion, email, telefono, es_miembro_comite "
          + "FROM congresista WHERE email = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, nombre, apellido, institucion, email, telefono, es_miembro_comite "
          + "FROM congresista ORDER BY nombre ASC";

  private static final String SQL_DELETE = "DELETE FROM congresista WHERE id = ?";

  private final Connection connection;

  @Override
  public CongresistaModel save(final CongresistaModel congresista) {
    final CongresistaPersistenceDto dto =
        CongresistaPersistenceMapper.fromModelToDto(congresista);
    final Long generatedId = executeSave(dto);
    return findByIdOrFail(new CongresistaId(generatedId));
  }

  @Override
  public CongresistaModel update(final CongresistaModel congresista) {
    final CongresistaPersistenceDto dto =
        CongresistaPersistenceMapper.fromModelToDto(congresista);
    executeUpdate(dto);
    return findByIdOrFail(congresista.getId());
  }

  @Override
  public Optional<CongresistaModel> getById(final CongresistaId congresistaId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setLong(1, congresistaId.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(CongresistaPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindByIdFailed(
          String.valueOf(congresistaId.value()), exception);
    }
  }

  @Override
  public Optional<CongresistaModel> getByEmail(final CongresistaEmail email) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
      statement.setString(1, email.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(CongresistaPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindByEmailFailed(email.value(), exception);
    }
  }

  @Override
  public List<CongresistaModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return CongresistaPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final CongresistaId congresistaId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setLong(1, congresistaId.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDeleteFailed(
          String.valueOf(congresistaId.value()), exception);
    }
  }

  private Long executeSave(final CongresistaPersistenceDto dto) {
    try (final PreparedStatement statement =
        connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, dto.nombre());
      statement.setString(2, dto.apellido());
      statement.setString(3, dto.institucion());
      statement.setString(4, dto.email());
      statement.setString(5, dto.telefono());
      statement.setBoolean(6, dto.esMiembroComite());
      statement.executeUpdate();
      try (final ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          return generatedKeys.getLong(1);
        }
        throw PersistenceException.becauseSaveFailed(
            "unknown", new SQLException("No se genero una clave"));
      }
    } catch (final SQLException exception) {
      throw PersistenceException.becauseSaveFailed(dto.email(), exception);
    }
  }

  private void executeUpdate(final CongresistaPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.nombre());
      statement.setString(2, dto.apellido());
      statement.setString(3, dto.institucion());
      statement.setString(4, dto.email());
      statement.setString(5, dto.telefono());
      statement.setBoolean(6, dto.esMiembroComite());
      statement.setLong(7, dto.id());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseUpdateFailed(String.valueOf(dto.id()), exception);
    }
  }

  private CongresistaModel findByIdOrFail(final CongresistaId congresistaId) {
    return getById(congresistaId)
        .orElseThrow(
            () -> CongresistaNotFoundException.becauseIdWasNotFound(congresistaId.value()));
  }
}
