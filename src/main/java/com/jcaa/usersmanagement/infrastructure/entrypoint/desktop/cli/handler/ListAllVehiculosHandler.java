package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListAllVehiculosHandler implements OperationHandler {

    private final VehiculoController vehiculoController;
    private final ConsoleIO console;
    private final VehiculoResponsePrinter printer;

    @Override
    public void handle() {
        console.println("\n  VEHICLE LIST");
        printer.printList(vehiculoController.listAllVehiculos());
    }
}