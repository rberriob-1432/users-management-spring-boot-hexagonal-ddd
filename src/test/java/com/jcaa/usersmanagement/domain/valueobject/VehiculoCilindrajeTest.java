package com.jcaa.usersmanagement.domain.valueobject;
import com.jcaa.usersmanagement.domain.exception.InvalidCilindrajeException;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoCilindraje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoCilindrajeTest {

    @Test
    void shouldCreateValidCilindraje() {
        VehiculoCilindraje cilindraje = new VehiculoCilindraje(1600);

        assertEquals(1600, cilindraje.value());
    }

    @Test
    void shouldThrowExceptionWhenCilindrajeIsZero() {
        assertThrows(
                InvalidCilindrajeException.class,
                () -> new VehiculoCilindraje(0)
        );
    }

    @Test
    void shouldThrowExceptionWhenCilindrajeIsNegative() {
        assertThrows(
                InvalidCilindrajeException.class,
                () -> new VehiculoCilindraje(-1600)
        );
    }

    @Test
    void shouldReturnCilindrajeAsString() {
        VehiculoCilindraje cilindraje = new VehiculoCilindraje(1600);

        assertEquals("1600 cc", cilindraje.toString());
    }
}