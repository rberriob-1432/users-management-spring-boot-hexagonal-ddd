package com.jcaa.usersmanagement.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidPlacaExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidPlacaException exception =
                InvalidPlacaException.becauseValueIsInvalid("ABC-123");

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "La placa del vehículo 'ABC-123' no es válida.",
                exception.getMessage()
        );
    }
}
