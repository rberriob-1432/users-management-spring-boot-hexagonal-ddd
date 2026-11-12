package com.jcaa.usersmanagement.domain.exception;

import com.jcaa.usersmanagement.domain.exception.InvalidMarcaException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidMarcaExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidMarcaException exception =
                InvalidMarcaException.becauseMarcaIsInvalid();

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "La marca del vehículo es inválida.",
                exception.getMessage()
        );
    }
}