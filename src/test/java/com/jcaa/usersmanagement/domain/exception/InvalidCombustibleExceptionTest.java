package com.jcaa.usersmanagement.domain.exception;

import com.jcaa.usersmanagement.domain.exception.InvalidCombustibleException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidCombustibleExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidCombustibleException exception =
                InvalidCombustibleException.becauseCombustibleIsInvalid();

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "El tipo de combustible es inválido.",
                exception.getMessage()
        );
    }
}