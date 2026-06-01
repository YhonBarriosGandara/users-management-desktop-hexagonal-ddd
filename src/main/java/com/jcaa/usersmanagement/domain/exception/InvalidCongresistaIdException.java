package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCongresistaIdException extends DomainException {

  private static final String MESSAGE_NOT_POSITIVE = "CongresistaId must be a positive number, but was: %d.";

  private InvalidCongresistaIdException(final String message) {
    super(message);
  }

  public static InvalidCongresistaIdException becauseValueIsNotPositive(final Long value) {
    return new InvalidCongresistaIdException(String.format(MESSAGE_NOT_POSITIVE, value));
  }
}
