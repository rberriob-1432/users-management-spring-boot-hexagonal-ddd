package com.jcaa.usersmanagement.domain.exception;
public final class InvalidKilometrosException extends DomainException {

    private static final String MESSAGE = "El kilometraje no puede ser negativo.";

    private InvalidKilometrosException(final String message) {
        super(message);
    }

    public static InvalidKilometrosException becauseKilometrosIsInvalid() {
        return new InvalidKilometrosException(MESSAGE);
    }
}