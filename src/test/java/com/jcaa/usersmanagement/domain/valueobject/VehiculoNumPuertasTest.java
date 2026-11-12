package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNumPuertasException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoNumPuertasTest {

    @Test
    void shouldCreateValidNumPuertas() {
        VehiculoNumPuertas puertas = new VehiculoNumPuertas(4);

        assertEquals(4, puertas.value());
    }

    @Test
    void shouldAllowZeroDoors() {
        VehiculoNumPuertas puertas = new VehiculoNumPuertas(0);

        assertEquals(0, puertas.value());
    }

    @Test
    void shouldThrowExceptionWhenNumPuertasIsNegative() {
        assertThrows(
                InvalidNumPuertasException.class,
                () -> new VehiculoNumPuertas(-1)
        );
    }

    @Test
    void shouldReturnNumPuertasAsString() {
        VehiculoNumPuertas puertas = new VehiculoNumPuertas(4);

        assertEquals("4", puertas.toString());
    }
}