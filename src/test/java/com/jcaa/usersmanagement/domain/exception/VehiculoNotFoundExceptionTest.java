package com.jcaa.usersmanagement.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {

        VehiculoNotFoundException exception =
                VehiculoNotFoundException.becausePlateWasNotFound("ABC123");

        assertNotNull(exception);

        assertInstanceOf(DomainException.class, exception);

        assertEquals(
                "The vehicle with plate 'ABC123' was not found.",
                exception.getMessage()
        );
    }

    @Test
    void shouldCreateExceptionWithDifferentPlate() {

        VehiculoNotFoundException exception =
                VehiculoNotFoundException.becausePlateWasNotFound("XYZ789");

        assertEquals(
                "The vehicle with plate 'XYZ789' was not found.",
                exception.getMessage()
        );
    }
}
