package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorAutorQuery;
import com.jcaa.usersmanagement.application.service.dto.query.trabajo.ListTrabajosPorPalabraClaveQuery;
import com.jcaa.usersmanagement.domain.valueobject.TrabajoId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TrabajoApplicationMapper {

  public Long fromListTrabajosPorAutorQueryToAutorId(final ListTrabajosPorAutorQuery query) {
    return query.autorId();
  }

  public String fromListTrabajosPorPalabraClaveQueryToPalabraClave(
      final ListTrabajosPorPalabraClaveQuery query) {
    return query.palabraClave();
  }

  public TrabajoId fromIdToTrabajoId(final Long id) {
    return new TrabajoId(id);
  }
}
