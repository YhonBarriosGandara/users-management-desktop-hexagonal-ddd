package com.jcaa.usersmanagement.domain.exception;

public final class CongresistaNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The congresista with id '%d' was not found.";

  private CongresistaNotFoundException(final String message) {
    super(message);
  }

  public static CongresistaNotFoundException becauseIdWasNotFound(final Long congresistaId) {
    return new CongresistaNotFoundException(String.format(MESSAGE_BY_ID, congresistaId));
  }
}
