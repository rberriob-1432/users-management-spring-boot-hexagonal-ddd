package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidVersionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoVersionTest {

    @Test
    void shouldCreateValidVersion() {
        VehiculoVersion version = new VehiculoVersion("SE-GR");

        assertEquals("SE-GR", version.value());
    }

    @Test
    void shouldRemoveExternalSpaces() {
        VehiculoVersion version = new VehiculoVersion(" SE-GR ");

        assertEquals("SE-GR", version.value());
    }

    @Test
    void shouldThrowExceptionWhenVersionIsEmpty() {
        assertThrows(
                InvalidVersionException.class,
                () -> new VehiculoVersion("")
        );
    }

    @Test
    void shouldThrowExceptionWhenVersionContainsOnlySpaces() {
        assertThrows(
                InvalidVersionException.class,
                () -> new VehiculoVersion("   ")
        );
    }

    @Test
    void shouldThrowExceptionWhenVersionIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> new VehiculoVersion(null)
        );
    }

    @Test
    void shouldReturnVersionAsString() {
        VehiculoVersion version = new VehiculoVersion("SE-GR");

        assertEquals("SE-GR", version.toString());
    }
}