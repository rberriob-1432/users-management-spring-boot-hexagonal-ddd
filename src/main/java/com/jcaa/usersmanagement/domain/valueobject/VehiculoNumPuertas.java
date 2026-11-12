package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNumPuertasException;

public record VehiculoNumPuertas(int value) {

    public VehiculoNumPuertas {
        validateNotNegative(value);
    }

    private static void validateNotNegative(final int value) {
        if (value < 0) {
            throw InvalidNumPuertasException.becauseNumPuertasIsInvalid();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}