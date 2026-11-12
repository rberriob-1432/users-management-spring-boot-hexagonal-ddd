package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidCategoriaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoCategoriaTest {

    @Test
    void shouldReturnAutomovil() {
        assertEquals(
                VehiculoCategoria.AUTOMOVIL,
                VehiculoCategoria.fromString("AUTOMOVIL")
        );
    }

    @Test
    void shouldIgnoreUpperAndLowerCase() {
        assertEquals(
                VehiculoCategoria.MOTOCICLETA,
                VehiculoCategoria.fromString("motocicleta")
        );
    }

    @Test
    void shouldRemoveExternalSpaces() {
        assertEquals(
                VehiculoCategoria.CAMIONETA,
                VehiculoCategoria.fromString(" CAMIONETA ")
        );
    }

    @Test
    void shouldReturnAllAvailableCategories() {
        assertEquals(6, VehiculoCategoria.values().length);
    }

    @Test
    void shouldThrowExceptionWhenCategoriaIsInvalid() {
        InvalidCategoriaException exception = assertThrows(
                InvalidCategoriaException.class,
                () -> VehiculoCategoria.fromString("BICICLETA")
        );

        assertEquals(
                "La categoría del vehículo 'BICICLETA' no es válida.",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenCategoriaIsEmpty() {
        InvalidCategoriaException exception = assertThrows(
                InvalidCategoriaException.class,
                () -> VehiculoCategoria.fromString("")
        );

        assertEquals(
                "La categoría del vehículo '' no es válida.",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenCategoriaIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> VehiculoCategoria.fromString(null)
        );
    }
}
