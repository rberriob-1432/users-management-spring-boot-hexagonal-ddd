package com.jcaa.usersmanagement.domain.exception;
import com.jcaa.usersmanagement.domain.exception.InvalidNumPuestosException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidNumPuestosExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidNumPuestosException exception =
                InvalidNumPuestosException.becauseNumPuestosIsInvalid();

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "El número de puestos debe ser mayor que cero.",
                exception.getMessage()
        );
    }
}