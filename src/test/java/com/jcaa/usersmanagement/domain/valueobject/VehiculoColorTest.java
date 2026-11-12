package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidColorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoColorTest {

    @Test
    void shouldCreateValidColor() {
        VehiculoColor color = new VehiculoColor("Rojo");

        assertEquals("Rojo", color.value());
    }

    @Test
    void shouldRemoveExternalSpaces() {
        VehiculoColor color = new VehiculoColor(" Rojo ");

        assertEquals("Rojo", color.value());
    }

    @Test
    void shouldThrowExceptionWhenColorIsEmpty() {
        assertThrows(
                InvalidColorException.class,
                () -> new VehiculoColor("")
        );
    }

    @Test
    void shouldThrowExceptionWhenColorContainsOnlySpaces() {
        assertThrows(
                InvalidColorException.class,
                () -> new VehiculoColor("   ")
        );
    }

    @Test
    void shouldThrowExceptionWhenColorIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> new VehiculoColor(null)
        );
    }

    @Test
    void shouldReturnColorAsString() {
        VehiculoColor color = new VehiculoColor("Rojo");

        assertEquals("Rojo", color.toString());
    }
}