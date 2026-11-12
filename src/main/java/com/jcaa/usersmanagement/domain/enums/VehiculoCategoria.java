package com.jcaa.usersmanagement.domain.enums;
import com.jcaa.usersmanagement.domain.exception.InvalidCategoriaException;

import java.util.Objects;

public enum VehiculoCategoria {

    AUTOMOVIL,
    MOTOCICLETA,
    CAMIONETA,
    CAMION,
    BUS,
    VAN;

    public static VehiculoCategoria fromString(final String value) {

        Objects.requireNonNull(value, "Categoria cannot be null");

        for (final VehiculoCategoria categoria : values()) {
            if (categoria.name().equalsIgnoreCase(value.trim())) {
                return categoria;
            }
        }

        throw InvalidCategoriaException.becauseValueIsInvalid(value);
    }
}