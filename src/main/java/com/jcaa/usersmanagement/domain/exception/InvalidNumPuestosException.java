package com.jcaa.usersmanagement.domain.exception;
public final class InvalidNumPuestosException extends DomainException {

    private static final String MESSAGE = "El número de puestos debe ser mayor que cero.";

    private InvalidNumPuestosException(final String message) {
        super(message);
    }

    public static InvalidNumPuestosException becauseNumPuestosIsInvalid() {
        return new InvalidNumPuestosException(MESSAGE);
    }
}