package com.jcaa.usersmanagement.domain.exception;

import com.jcaa.usersmanagement.domain.exception.InvalidNumPuertasException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidNumPuertasExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidNumPuertasException exception =
                InvalidNumPuertasException.becauseNumPuertasIsInvalid();

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "El número de puertas es inválido.",
                exception.getMessage()
        );
    }
}