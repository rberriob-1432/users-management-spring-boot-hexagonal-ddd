package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidKilometrosException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoKilometrosTest {

    @Test
    void shouldCreateValidKilometros() {
        VehiculoKilometros kilometros = new VehiculoKilometros(50000);

        assertEquals(50000, kilometros.value());
    }

    @Test
    void shouldAllowZeroKilometros() {
        VehiculoKilometros kilometros = new VehiculoKilometros(0);

        assertEquals(0, kilometros.value());
    }

    @Test
    void shouldThrowExceptionWhenKilometrosIsNegative() {
        assertThrows(
                InvalidKilometrosException.class,
                () -> new VehiculoKilometros(-1)
        );
    }

    @Test
    void shouldReturnKilometrosAsString() {
        VehiculoKilometros kilometros = new VehiculoKilometros(50000);

        assertEquals("50000", kilometros.toString());
    }
}