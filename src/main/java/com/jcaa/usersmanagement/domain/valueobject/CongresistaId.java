package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidCongresistaIdException;
import java.util.Objects;

public record CongresistaId(Long value) {

  public CongresistaId {
    Objects.requireNonNull(value, "CongresistaId cannot be null");
    if (value <= 0) {
      throw InvalidCongresistaIdException.becauseValueIsNotPositive(value);
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
