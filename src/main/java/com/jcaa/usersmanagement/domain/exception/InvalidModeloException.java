package com.jcaa.usersmanagement.domain.exception;
public final class InvalidModeloException extends DomainException {

    private static final String MESSAGE = "El modelo del vehículo es inválido.";

    private InvalidModeloException(final String message) {
        super(message);
    }

    public static InvalidModeloException becauseModeloIsInvalid() {
        return new InvalidModeloException(MESSAGE);
    }
}