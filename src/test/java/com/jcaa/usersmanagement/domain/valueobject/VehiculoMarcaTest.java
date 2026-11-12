package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMarcaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoMarcaTest {

    @Test
    void shouldCreateValidMarca() {
        VehiculoMarca marca = new VehiculoMarca("Toyota");

        assertEquals("Toyota", marca.value());
    }

    @Test
    void shouldRemoveExternalSpaces() {
        VehiculoMarca marca = new VehiculoMarca(" Toyota ");

        assertEquals("Toyota", marca.value());
    }

    @Test
    void shouldThrowExceptionWhenMarcaIsEmpty() {
        assertThrows(
                InvalidMarcaException.class,
                () -> new VehiculoMarca("")
        );
    }

    @Test
    void shouldThrowExceptionWhenMarcaContainsOnlySpaces() {
        assertThrows(
                InvalidMarcaException.class,
                () -> new VehiculoMarca("   ")
        );
    }

    @Test
    void shouldThrowExceptionWhenMarcaIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> new VehiculoMarca(null)
        );
    }

    @Test
    void shouldReturnMarcaAsString() {
        VehiculoMarca marca = new VehiculoMarca("Toyota");

        assertEquals("Toyota", marca.toString());
    }
}