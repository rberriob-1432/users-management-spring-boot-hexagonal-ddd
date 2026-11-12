package com.jcaa.usersmanagement.domain.valueobject;
import com.jcaa.usersmanagement.domain.exception.InvalidModeloException;
import java.util.Objects;

public record VehiculoModelo(String value) {

    public VehiculoModelo {
        value = Objects.requireNonNull(value, "Modelo cannot be null").trim();

        validateNotEmpty(value);
    }

    private static void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidModeloException.becauseModeloIsInvalid();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}