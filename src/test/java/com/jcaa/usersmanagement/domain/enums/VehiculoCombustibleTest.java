package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidCombustibleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoCombustibleTest {

    @Test
    void shouldReturnGasolina() {
        assertEquals(
                VehiculoCombustible.GASOLINA,
                VehiculoCombustible.fromString("GASOLINA")
        );
    }

    @Test
    void shouldIgnoreUpperAndLowerCase() {
        assertEquals(
                VehiculoCombustible.GASOLINA,
                VehiculoCombustible.fromString("gasolina")
        );
    }

    @Test
    void shouldRemoveExternalSpaces() {
        assertEquals(
                VehiculoCombustible.DIESEL,
                VehiculoCombustible.fromString(" DIESEL ")
        );
    }

    @Test
    void shouldReturnAllAvailableFuelTypes() {
        assertEquals(6, VehiculoCombustible.values().length);
    }

    @Test
    void shouldThrowExceptionWhenCombustibleIsInvalid() {
        assertThrows(
                InvalidCombustibleException.class,
                () -> VehiculoCombustible.fromString("AGUA")
        );
    }

    @Test
    void shouldThrowExceptionWhenCombustibleIsEmpty() {
        assertThrows(
                InvalidCombustibleException.class,
                () -> VehiculoCombustible.fromString("")
        );
    }

    @Test
    void shouldThrowExceptionWhenCombustibleIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> VehiculoCombustible.fromString(null)
        );
    }
}