package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMarcaException;
import java.util.Objects;

public record VehiculoMarca(String value) {

    public VehiculoMarca {
        value = Objects.requireNonNull(value, "Marca cannot be null").trim();

        validateNotEmpty(value);
    }

    private static void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidMarcaException.becauseMarcaIsInvalid();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}