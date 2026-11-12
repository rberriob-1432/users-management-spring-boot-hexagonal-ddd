
package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateVehiculoRestRequest(

        @NotBlank(message = "marca must not be blank")
        String marca,

        @NotBlank(message = "modelo must not be blank")
        String modelo,

        @NotBlank(message = "version must not be blank")
        String version,

        @NotBlank(message = "color must not be blank")
        String color,

        @Positive(message = "numPuestos must be greater than zero")
        int numPuestos,

        @PositiveOrZero(message = "numPuertas must be zero or greater")
        int numPuertas,

        @NotBlank(message = "combustible must not be blank")
        String combustible,

        @PositiveOrZero(message = "kilometros must be zero or greater")
        int kilometros,

        @Positive(message = "cilindraje must be greater than zero")
        int cilindraje,

        @NotBlank(message = "categoria must not be blank")
        String categoria

) {}
