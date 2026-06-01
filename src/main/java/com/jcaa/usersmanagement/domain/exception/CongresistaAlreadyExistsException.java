package com.jcaa.usersmanagement.domain.exception;

public final class CongresistaAlreadyExistsException extends DomainException {

  private static final String MESSAGE_EMAIL_EXISTS =
      "Ya existe un congresista con el email '%s'.";

  private CongresistaAlreadyExistsException(final String message) {
    super(message);
  }

  public static CongresistaAlreadyExistsException becauseEmailAlreadyExists(
      final String email) {
    return new CongresistaAlreadyExistsException(
        String.format(MESSAGE_EMAIL_EXISTS, email));
  }
}
