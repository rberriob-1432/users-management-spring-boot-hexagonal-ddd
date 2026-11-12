package com.jcaa.usersmanagement.domain.exception;
public final class InvalidCilindrajeException extends DomainException {

    private static final String MESSAGE = "El cilindraje debe ser mayor que cero.";

    private InvalidCilindrajeException(final String message) {
        super(message);
    }

    public static InvalidCilindrajeException becauseCilindrajeIsInvalid() {
        return new InvalidCilindrajeException(MESSAGE);
    }
}