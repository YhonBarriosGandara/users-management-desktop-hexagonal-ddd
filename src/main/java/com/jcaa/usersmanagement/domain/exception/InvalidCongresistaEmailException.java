package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCongresistaEmailException extends DomainException {

  private static final String MESSAGE_EMPTY = "CongresistaEmail must not be empty.";
  private static final String MESSAGE_FORMAT = "CongresistaEmail '%s' has an invalid format.";

  private InvalidCongresistaEmailException(final String message) {
    super(message);
  }

  public static InvalidCongresistaEmailException becauseValueIsEmpty() {
    return new InvalidCongresistaEmailException(MESSAGE_EMPTY);
  }

  public static InvalidCongresistaEmailException becauseFormatIsInvalid(final String value) {
    return new InvalidCongresistaEmailException(String.format(MESSAGE_FORMAT, value));
  }
}
