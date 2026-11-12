
package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateVehiculoRequest(
        String placa,
        String marca,
        String modelo,
        String version,
        String color,
        Integer numPuestos,
        Integer numPuertas,
        String combustible,
        Integer kilometros,
        Integer cilindraje,
        String categoria
) {}
