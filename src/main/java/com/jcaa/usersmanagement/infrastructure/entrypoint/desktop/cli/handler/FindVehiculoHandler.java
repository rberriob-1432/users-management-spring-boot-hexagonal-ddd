package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.VehiculoNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.VehiculoResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindVehiculoHandler implements OperationHandler {

    private final VehiculoController vehiculoController;
    private final ConsoleIO console;
    private final VehiculoResponsePrinter printer;

    @Override
    public void handle() {
        final String placa = console.readRequired("Enter vehicle plate: ");

        try {
            final VehiculoResponse vehiculo =
                    vehiculoController.findVehiculoByPlaca(placa);

            console.println("\n  Vehicle found successfully.");
            printer.print(vehiculo);

        } catch (final VehiculoNotFoundException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }
}
