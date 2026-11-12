package com.jcaa.usersmanagement.domain.exception;

public final class VehiculoNotFoundException extends DomainException {

    private static final String MESSAGE_BY_PLACA =
            "The vehicle with plate '%s' was not found.";

    private VehiculoNotFoundException(final String message) {
        super(message);
    }

    public static VehiculoNotFoundException becausePlateWasNotFound(
            final String placa) {

        return new VehiculoNotFoundException(
                String.format(MESSAGE_BY_PLACA, placa)
        );
    }
}
