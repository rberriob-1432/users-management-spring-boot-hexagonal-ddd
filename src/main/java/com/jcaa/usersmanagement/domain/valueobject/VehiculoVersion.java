package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidVersionException;
import java.util.Objects;

public record VehiculoVersion(String value) {

    public VehiculoVersion {
        value = Objects.requireNonNull(value, "Version cannot be null").trim();

        validateNotEmpty(value);
    }

    private static void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw InvalidVersionException.becauseVersionIsInvalid();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}