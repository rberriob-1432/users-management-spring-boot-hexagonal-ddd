package com.jcaa.usersmanagement.domain.exception;
public final class InvalidVersionException extends DomainException {

    private static final String MESSAGE = "La versión del vehículo es inválida.";

    private InvalidVersionException(final String message) {
        super(message);
    }

    public static InvalidVersionException becauseVersionIsInvalid() {
        return new InvalidVersionException(MESSAGE);
    }
}