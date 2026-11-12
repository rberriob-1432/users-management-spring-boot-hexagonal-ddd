package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidCilindrajeException;

public record VehiculoCilindraje(int value) {

    public VehiculoCilindraje {
        validatePositive(value);
    }

    private static void validatePositive(final int value) {
        if (value <= 0) {
            throw InvalidCilindrajeException.becauseCilindrajeIsInvalid();
        }
    }

    @Override
    public String toString() {
        return value + " cc";
    }
}