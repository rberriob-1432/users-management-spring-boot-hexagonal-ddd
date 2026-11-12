package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNumPuestosException;

public record VehiculoNumPuestos(int value) {

    public VehiculoNumPuestos {
        validatePositive(value);
    }

    private static void validatePositive(final int value) {
        if (value <= 0) {
            throw InvalidNumPuestosException.becauseNumPuestosIsInvalid();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}