
package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidPlacaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoPlacaTest {

    @Test
    void shouldCreateValidPlaca() {
        VehiculoPlaca placa = new VehiculoPlaca("abc123");

        assertEquals("ABC123", placa.value());
    }

    @Test
    void shouldNormalizePlaca() {
        VehiculoPlaca placa = new VehiculoPlaca(" abc123 ");

        assertEquals("ABC123", placa.value());
    }

    @Test
    void shouldThrowExceptionWhenPlacaIsEmpty() {
        assertThrows(
                InvalidPlacaException.class,
                () -> new VehiculoPlaca("")
        );
    }

    @Test
    void shouldThrowExceptionWhenPlacaIsTooShort() {
        assertThrows(
                InvalidPlacaException.class,
                () -> new VehiculoPlaca("ABC")
        );
    }

    @Test
    void shouldThrowExceptionWhenPlacaIsTooLong() {
        assertThrows(
                InvalidPlacaException.class,
                () -> new VehiculoPlaca("ABCDEFGH")
        );
    }

    @Test
    void shouldThrowExceptionWhenPlacaHasInvalidCharacters() {
        assertThrows(
                InvalidPlacaException.class,
                () -> new VehiculoPlaca("ABC-123")
        );
    }

    @Test
    void shouldThrowExceptionWhenPlacaIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> new VehiculoPlaca(null)
        );
    }

    @Test
    void shouldReturnPlacaAsString() {
        VehiculoPlaca placa = new VehiculoPlaca("ABC123");

        assertEquals("ABC123", placa.toString());
    }

    @Test
    void shouldAcceptMinimumLengthPlaca() {
        VehiculoPlaca placa = new VehiculoPlaca("ABC12");

        assertEquals("ABC12", placa.value());
    }

    @Test
    void shouldAcceptMaximumLengthPlaca() {
        VehiculoPlaca placa = new VehiculoPlaca("ABC1234");

        assertEquals("ABC1234", placa.value());
    }
}
