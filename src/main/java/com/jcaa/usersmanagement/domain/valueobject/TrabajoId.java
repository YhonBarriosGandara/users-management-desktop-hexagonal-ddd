package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidTrabajoIdException;
import java.util.Objects;

public record TrabajoId(Long value) {

  public TrabajoId {
    Objects.requireNonNull(value, "TrabajoId cannot be null");
    if (value <= 0) {
      throw InvalidTrabajoIdException.becauseValueIsNotPositive(value);
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
