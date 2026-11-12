package com.jcaa.usersmanagement.domain.exception;
import com.jcaa.usersmanagement.domain.exception.InvalidModeloException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidModeloExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidModeloException exception =
                InvalidModeloException.becauseModeloIsInvalid();

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "El modelo del vehículo es inválido.",
                exception.getMessage()
        );
    }
}