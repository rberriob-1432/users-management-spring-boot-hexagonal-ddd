package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidKilometrosException;

public record VehiculoKilometros(int value) {

    public VehiculoKilometros {
        validateNotNegative(value);
    }

    private static void validateNotNegative(final int value) {
        if (value < 0) {
            throw InvalidKilometrosException.becauseKilometrosIsInvalid();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}