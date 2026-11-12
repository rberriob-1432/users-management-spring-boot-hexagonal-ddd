package com.jcaa.usersmanagement.domain.exception;
public final class InvalidMarcaException extends DomainException {

    private static final String MESSAGE = "La marca del vehículo es inválida.";

    private InvalidMarcaException(final String message) {
        super(message);
    }

    public static InvalidMarcaException becauseMarcaIsInvalid() {
        return new InvalidMarcaException(MESSAGE);
    }
}