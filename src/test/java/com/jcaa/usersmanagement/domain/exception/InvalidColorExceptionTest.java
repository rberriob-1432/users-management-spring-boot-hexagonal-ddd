package com.jcaa.usersmanagement.domain.exception;
import com.jcaa.usersmanagement.domain.exception.DomainException;
import com.jcaa.usersmanagement.domain.exception.InvalidColorException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidColorExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidColorException exception =
                InvalidColorException.becauseColorIsInvalid();

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "El color del vehículo es inválido.",
                exception.getMessage()
        );
    }
}