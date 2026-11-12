
package com.jcaa.usersmanagement.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCategoriaExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        InvalidCategoriaException exception =
                InvalidCategoriaException.becauseValueIsInvalid("DEPORTIVO");

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "La categoría del vehículo 'DEPORTIVO' no es válida.",
                exception.getMessage()
        );
    }
}
