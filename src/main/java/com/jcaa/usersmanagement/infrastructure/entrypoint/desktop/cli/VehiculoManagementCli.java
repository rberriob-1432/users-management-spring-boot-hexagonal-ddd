
package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateVehiculoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteVehiculoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindVehiculoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListAllVehiculosHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateVehiculoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.VehiculoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class VehiculoManagementCli {

    private static final String BANNER =
            """
            ==========================================
                 Vehicle Management System
            ==========================================""";

    private static final String MENU_BORDER =
            "  ==========================================";

    private final VehiculoController vehiculoController;
    private final ConsoleIO console;

    public void start() {
        console.println(BANNER);

        final VehiculoResponsePrinter printer =
                new VehiculoResponsePrinter(console);

        runLoop(buildHandlers(printer));
    }

    private void runLoop(
            final Map<MenuOption, OperationHandler> handlers) {

        boolean running = true;

        while (running) {
            printMenu(handlers);

            final int choice = console.readInt("\n  Option: ");
            final Optional<MenuOption> option =
                    MenuOption.fromNumber(choice);

            if (option.isEmpty()) {
                console.println("  Invalid option. Please try again.");

            } else if (option.get() == MenuOption.EXIT) {
                console.println("\n  Goodbye!\n");
                running = false;

            } else {
                executeHandler(handlers, option.get());
            }
        }
    }

    private void executeHandler(
            final Map<MenuOption, OperationHandler> handlers,
            final MenuOption option) {

        final OperationHandler handler = handlers.get(option);

        if (handler == null) {
            console.println("  Invalid option for this menu.");
            return;
        }

        try {
            handler.handle();

        } catch (final ConstraintViolationException exception) {
            console.println("  Validation errors:");

            exception.getConstraintViolations()
                    .forEach(violation ->
                            console.println("    - " + violation.getMessage()));

        } catch (final RuntimeException exception) {
            console.println(
                    "  Unexpected error: " + exception.getMessage());
        }
    }

    private Map<MenuOption, OperationHandler> buildHandlers(
            final VehiculoResponsePrinter printer) {

        return Map.of(
                MenuOption.LIST_VEHICULOS,
                new ListAllVehiculosHandler(
                        vehiculoController, console, printer),

                MenuOption.FIND_VEHICULO,
                new FindVehiculoHandler(
                        vehiculoController, console, printer),

                MenuOption.CREATE_VEHICULO,
                new CreateVehiculoHandler(
                        vehiculoController, console, printer),

                MenuOption.UPDATE_VEHICULO,
                new UpdateVehiculoHandler(
                        vehiculoController, console, printer),

                MenuOption.DELETE_VEHICULO,
                new DeleteVehiculoHandler(
                        vehiculoController, console)
        );
    }

    private void printMenu(
            final Map<MenuOption, OperationHandler> handlers) {

        console.println();
        console.println(MENU_BORDER);
        console.println("    VEHICLE MANAGEMENT");
        console.println(MENU_BORDER);

        for (final MenuOption option : MenuOption.values()) {
            if (option == MenuOption.EXIT || handlers.containsKey(option)) {
                console.printf(
                        "    [%d] %s%n",
                        option.getNumber(),
                        option.getDescription());
            }
        }

        console.println(MENU_BORDER);
    }
}
