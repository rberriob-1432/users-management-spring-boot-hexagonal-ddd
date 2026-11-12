package com.jcaa.usersmanagement.domain.exception;
import com.jcaa.usersmanagement.domain.exception.InvalidCilindrajeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidCilindrajeExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidCilindrajeException exception =
                InvalidCilindrajeException.becauseCilindrajeIsInvalid();

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "El cilindraje debe ser mayor que cero.",
                exception.getMessage()
        );
    }
}