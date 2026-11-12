package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.VehiculoCategoria;
import com.jcaa.usersmanagement.domain.enums.VehiculoCombustible;
import com.jcaa.usersmanagement.domain.valueobject.*;
import lombok.Value;

@Value
public class VehiculoModel {

    VehiculoPlaca placa;
    VehiculoMarca marca;
    VehiculoModelo modelo;
    VehiculoVersion version;
    VehiculoColor color;
    VehiculoNumPuestos numPuestos;
    VehiculoNumPuertas numPuertas;
    VehiculoCombustible combustible;
    VehiculoKilometros kilometros;
    VehiculoCilindraje cilindraje;
    VehiculoCategoria categoria;

    public static VehiculoModel create(
            final VehiculoPlaca placa,
            final VehiculoMarca marca,
            final VehiculoModelo modelo,
            final VehiculoVersion version,
            final VehiculoColor color,
            final VehiculoNumPuestos numPuestos,
            final VehiculoNumPuertas numPuertas,
            final VehiculoCombustible combustible,
            final VehiculoKilometros kilometros,
            final VehiculoCilindraje cilindraje,
            final VehiculoCategoria categoria
    ) {
        return new VehiculoModel(
                placa,
                marca,
                modelo,
                version,
                color,
                numPuestos,
                numPuertas,
                combustible,
                kilometros,
                cilindraje,
                categoria
        );
    }
}
