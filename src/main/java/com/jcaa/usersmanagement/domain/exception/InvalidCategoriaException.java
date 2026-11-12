package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCategoriaException extends DomainException {

    private static final String MESSAGE_INVALID =
            "La categoría del vehículo '%s' no es válida.";

    private InvalidCategoriaException(final String message) {
        super(message);
    }

    public static InvalidCategoriaException becauseValueIsInvalid(final String categoria) {
        return new InvalidCategoriaException(String.format(MESSAGE_INVALID, categoria));
    }
}