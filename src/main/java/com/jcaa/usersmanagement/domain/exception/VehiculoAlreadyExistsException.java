
package com.jcaa.usersmanagement.domain.exception;

public final class VehiculoAlreadyExistsException extends DomainException {

    private static final String MESSAGE =
            "A vehicle with plate '%s' already exists.";

    private VehiculoAlreadyExistsException(final String message) {
        super(message);
    }

    public static VehiculoAlreadyExistsException becausePlateAlreadyExists(
            final String placa) {

        return new VehiculoAlreadyExistsException(
                String.format(MESSAGE, placa)
        );
    }
}
