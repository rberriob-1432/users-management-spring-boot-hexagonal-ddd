package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidModeloException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoModeloTest {

    @Test
    void shouldCreateValidModelo() {
        VehiculoModelo modelo = new VehiculoModelo("Corolla");

        assertEquals("Corolla", modelo.value());
    }

    @Test
    void shouldRemoveExternalSpaces() {
        VehiculoModelo modelo = new VehiculoModelo(" Corolla ");

        assertEquals("Corolla", modelo.value());
    }

    @Test
    void shouldThrowExceptionWhenModeloIsEmpty() {
        assertThrows(
                InvalidModeloException.class,
                () -> new VehiculoModelo("")
        );
    }

    @Test
    void shouldThrowExceptionWhenModeloContainsOnlySpaces() {
        assertThrows(
                InvalidModeloException.class,
                () -> new VehiculoModelo("   ")
        );
    }

    @Test
    void shouldThrowExceptionWhenModeloIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> new VehiculoModelo(null)
        );
    }

    @Test
    void shouldReturnModeloAsString() {
        VehiculoModelo modelo = new VehiculoModelo("Corolla");

        assertEquals("Corolla", modelo.toString());
    }
}