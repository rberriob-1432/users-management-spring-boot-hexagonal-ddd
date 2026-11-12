package com.jcaa.usersmanagement.domain.exception;
public final class InvalidColorException extends DomainException {

    private static final String MESSAGE = "El color del vehículo es inválido.";

    private InvalidColorException(final String message) {
        super(message);
    }

    public static InvalidColorException becauseColorIsInvalid() {
        return new InvalidColorException(MESSAGE);
    }
}