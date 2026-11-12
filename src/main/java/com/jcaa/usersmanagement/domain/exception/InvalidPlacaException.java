package com.jcaa.usersmanagement.domain.exception;

public final class InvalidPlacaException extends DomainException {

    private static final String MESSAGE_INVALID =
            "La placa del vehículo '%s' no es válida.";

    private InvalidPlacaException(final String message) {
        super(message);
    }

    public static InvalidPlacaException becauseValueIsInvalid(final String placa) {
        return new InvalidPlacaException(
                String.format(MESSAGE_INVALID, placa)
        );
    }
}
