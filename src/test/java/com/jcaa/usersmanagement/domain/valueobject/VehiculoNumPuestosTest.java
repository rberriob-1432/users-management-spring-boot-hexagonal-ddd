package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNumPuestosException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoNumPuestosTest {

    @Test
    void shouldCreateValidNumPuestos() {
        VehiculoNumPuestos puestos = new VehiculoNumPuestos(5);

        assertEquals(5, puestos.value());
    }

    @Test
    void shouldThrowExceptionWhenNumPuestosIsZero() {
        assertThrows(
                InvalidNumPuestosException.class,
                () -> new VehiculoNumPuestos(0)
        );
    }

    @Test
    void shouldThrowExceptionWhenNumPuestosIsNegative() {
        assertThrows(
                InvalidNumPuestosException.class,
                () -> new VehiculoNumPuestos(-1)
        );
    }

    @Test
    void shouldReturnNumPuestosAsString() {
        VehiculoNumPuestos puestos = new VehiculoNumPuestos(5);

        assertEquals("5", puestos.toString());
    }
}