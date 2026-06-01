package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.congresista.CreateCongresistaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.congresista.DeleteCongresistaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.congresista.UpdateCongresistaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.congresista.GetCongresistaByIdQuery;
import com.jcaa.usersmanagement.domain.model.CongresistaModel;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaEmail;
import com.jcaa.usersmanagement.domain.valueobject.CongresistaId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CongresistaApplicationMapper {

  public CongresistaModel fromCreateCommandToModel(final CreateCongresistaCommand command) {
    final CongresistaEmail email = new CongresistaEmail(command.email());
    return CongresistaModel.create(
        null,
        command.nombre(),
        command.apellido(),
        command.institucion(),
        email,
        command.telefono(),
        command.esMiembroComite());
  }

  public CongresistaModel fromUpdateCommandToModel(final UpdateCongresistaCommand command) {
    return new CongresistaModel(
        new CongresistaId(command.id()),
        command.nombre(),
        command.apellido(),
        command.institucion(),
        new CongresistaEmail(command.email()),
        command.telefono(),
        command.esMiembroComite());
  }

  public CongresistaId fromGetCongresistaByIdQueryToId(final GetCongresistaByIdQuery query) {
    return new CongresistaId(query.id());
  }

  public CongresistaId fromDeleteCommandToId(final DeleteCongresistaCommand command) {
    return new CongresistaId(command.id());
  }
}
