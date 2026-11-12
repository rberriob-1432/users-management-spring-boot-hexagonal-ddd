package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record VehiculoPersistenceDto(
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
