package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidColorException;
import java.util.Objects;

public record VehiculoColor(String value) {

    public VehiculoColor {
        value = Objects.requireNonNull(value, "Color cannot be null").trim();

        validateNotEmpty(value);
    }

    private static void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidColorException.becauseColorIsInvalid();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}