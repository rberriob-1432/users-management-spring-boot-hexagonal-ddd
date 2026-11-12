package com.jcaa.usersmanagement.domain.exception;
public final class InvalidCombustibleException extends DomainException {

    private static final String MESSAGE = "El tipo de combustible es inválido.";

    private InvalidCombustibleException(final String message) {
        super(message);
    }

    public static InvalidCombustibleException becauseCombustibleIsInvalid() {
        return new InvalidCombustibleException(MESSAGE);
    }
}