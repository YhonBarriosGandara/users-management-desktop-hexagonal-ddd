package com.jcaa.usersmanagement.domain.exception;

public final class InvalidTrabajoIdException extends DomainException {

  private static final String MESSAGE_NOT_POSITIVE = "TrabajoId must be a positive number, but was '%d'.";

  private InvalidTrabajoIdException(final String message) {
    super(message);
  }

  public static InvalidTrabajoIdException becauseValueIsNotPositive(final Long value) {
    return new InvalidTrabajoIdException(String.format(MESSAGE_NOT_POSITIVE, value));
  }
}
