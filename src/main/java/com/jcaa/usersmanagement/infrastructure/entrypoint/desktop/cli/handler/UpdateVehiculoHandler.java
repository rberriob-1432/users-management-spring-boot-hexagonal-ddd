package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.VehiculoNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateVehiculoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.VehiculoResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateVehiculoHandler implements OperationHandler {

    private final VehiculoController vehiculoController;
    private final ConsoleIO console;
    private final VehiculoResponsePrinter printer;

    @Override
    public void handle() {
        final String placa = console.readRequired("Placa          : ");
        final String marca = console.readRequired("Marca          : ");
        final String modelo = console.readRequired("Modelo         : ");
        final String version = console.readRequired("Versión        : ");
        final String color = console.readRequired("Color          : ");
        final int numPuestos = console.readInt("Número puestos : ");
        final int numPuertas = console.readInt("Número puertas : ");
        final String combustible = console.readRequired("Combustible    : ");
        final int kilometros = console.readInt("Kilómetros     : ");
        final int cilindraje = console.readInt("Cilindraje     : ");
        final String categoria = console.readRequired("Categoría      : ");

        try {
            final VehiculoResponse updated =
                    vehiculoController.updateVehiculo(
                            new UpdateVehiculoRequest(
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
                            )
                    );

            console.println("\n  Vehicle updated successfully.");
            printer.print(updated);

        } catch (final VehiculoNotFoundException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}
