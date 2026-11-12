package com.jcaa.usersmanagement.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoAlreadyExistsExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {

        VehiculoAlreadyExistsException exception =
                VehiculoAlreadyExistsException
                        .becausePlateAlreadyExists("ABC123");

        assertNotNull(exception);
        assertInstanceOf(DomainException.class, exception);
        assertEquals(
                "A vehicle with plate 'ABC123' already exists.",
                exception.getMessage()
        );
    }

    @Test
    void shouldCreateExceptionWithDifferentPlate() {

        VehiculoAlreadyExistsException exception =
                VehiculoAlreadyExistsException
                        .becausePlateAlreadyExists("XYZ789");

        assertEquals(
                "A vehicle with plate 'XYZ789' already exists.",
                exception.getMessage()
        );
    }
}