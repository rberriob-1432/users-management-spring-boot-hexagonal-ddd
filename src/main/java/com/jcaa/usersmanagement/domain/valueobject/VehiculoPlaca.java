package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidPlacaException;

import java.util.Objects;

public record VehiculoPlaca(String value) {

    private static final int MINIMUM_LENGTH = 5;
    private static final int MAXIMUM_LENGTH = 7;

    public VehiculoPlaca {
        value = Objects.requireNonNull(value, "Placa cannot be null")
                .trim()
                .toUpperCase();

        validateNotEmpty(value);
        validateLength(value);
        validateFormat(value);
    }

    private static void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidPlacaException.becauseValueIsInvalid(value);
        }
    }

    private static void validateLength(final String value) {
        if (value.length() < MINIMUM_LENGTH ||
                value.length() > MAXIMUM_LENGTH) {
            throw InvalidPlacaException.becauseValueIsInvalid(value);
        }
    }

    private static void validateFormat(final String value) {
        if (!value.matches("[A-Z0-9]+")) {
            throw InvalidPlacaException.becauseValueIsInvalid(value);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
