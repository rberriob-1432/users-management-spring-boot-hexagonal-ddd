package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response;

public record VehiculoRestResponse(
        String placa,
        String marca,
        String modelo,
        String version,
        String color,
        int numPuestos,
        int numPuertas,
        String combustible,
        int kilometros,
        int cilindraje,
        String categoria
) {}
