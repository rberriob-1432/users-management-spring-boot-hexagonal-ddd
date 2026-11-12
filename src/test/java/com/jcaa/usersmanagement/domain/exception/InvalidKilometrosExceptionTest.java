package com.jcaa.usersmanagement.domain.exception;

import com.jcaa.usersmanagement.domain.exception.InvalidKilometrosException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidKilometrosExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidKilometrosException exception =
                InvalidKilometrosException.becauseKilometrosIsInvalid();

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "El kilometraje no puede ser negativo.",
                exception.getMessage()
        );
    }
}