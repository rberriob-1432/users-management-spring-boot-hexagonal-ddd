package com.jcaa.usersmanagement.domain.enums;
import com.jcaa.usersmanagement.domain.exception.InvalidCombustibleException;
import java.util.Objects;

public enum VehiculoCombustible {

    GASOLINA,
    DIESEL,
    GAS_NATURAL,
    GLP,
    ELECTRICO,
    HIBRIDO;

    public static VehiculoCombustible fromString(final String value) {

        Objects.requireNonNull(value, "Combustible cannot be null");

        for (final VehiculoCombustible combustible : values()) {
            if (combustible.name().equalsIgnoreCase(value.trim())) {
                return combustible;
            }
        }

        throw InvalidCombustibleException.becauseCombustibleIsInvalid();
    }
}