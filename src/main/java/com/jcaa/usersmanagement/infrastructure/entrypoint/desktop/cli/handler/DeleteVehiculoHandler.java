package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.VehiculoNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteVehiculoHandler implements OperationHandler {

    private final VehiculoController vehiculoController;
    private final ConsoleIO console;

    @Override
    public void handle() {
        final String placa = console.readRequired("Enter vehicle plate: ");

        try {
            vehiculoController.deleteVehiculo(placa);
            console.println("\n  Vehicle deleted successfully.");

        } catch (final VehiculoNotFoundException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}
