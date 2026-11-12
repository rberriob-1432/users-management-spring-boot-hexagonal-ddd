package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.VehiculoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class VehiculoResponsePrinter {

    private static final String SEPARATOR = "-".repeat(52);
    private static final String ROW_FORMAT = "  %-15s : %s%n";

    private final ConsoleIO console;

    public void print(final VehiculoResponse response) {
        console.println(SEPARATOR);
        console.printf(ROW_FORMAT, "Placa", response.placa());
        console.printf(ROW_FORMAT, "Marca", response.marca());
        console.printf(ROW_FORMAT, "Modelo", response.modelo());
        console.printf(ROW_FORMAT, "Version", response.version());
        console.printf(ROW_FORMAT, "Color", response.color());
        console.printf(ROW_FORMAT, "Puestos", response.numPuestos());
        console.printf(ROW_FORMAT, "Puertas", response.numPuertas());
        console.printf(ROW_FORMAT, "Combustible", response.combustible());
        console.printf(ROW_FORMAT, "Kilometros", response.kilometros());
        console.printf(ROW_FORMAT, "Cilindraje", response.cilindraje());
        console.printf(ROW_FORMAT, "Categoria", response.categoria());
        console.println(SEPARATOR);
    }

    public void printList(final List<VehiculoResponse> vehiculos) {
        if (vehiculos.isEmpty()) {
            console.println("  No vehicles found.");
            return;
        }

        console.printf("%n  Total: %d vehicle(s)%n", vehiculos.size());
        vehiculos.forEach(this::print);
    }
}
