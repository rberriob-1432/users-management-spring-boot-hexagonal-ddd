package com.jcaa.usersmanagement.domain.exception;

import com.jcaa.usersmanagement.domain.exception.InvalidVersionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidVersionExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidVersionException exception =
                InvalidVersionException.becauseVersionIsInvalid();

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "La versión del vehículo es inválida.",
                exception.getMessage()
        );
    }
}