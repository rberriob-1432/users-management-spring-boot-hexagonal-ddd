package com.jcaa.usersmanagement.domain.exception;
public final class InvalidNumPuertasException extends DomainException {

    private static final String MESSAGE = "El número de puertas es inválido.";

    private InvalidNumPuertasException(final String message) {
        super(message);
    }

    public static InvalidNumPuertasException becauseNumPuertasIsInvalid() {
        return new InvalidNumPuertasException(MESSAGE);
    }
}