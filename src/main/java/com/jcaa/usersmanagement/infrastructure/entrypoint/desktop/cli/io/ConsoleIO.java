package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import java.io.PrintStream;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ConsoleIO {

  private final Scanner scanner;
  private final PrintStream out;

  public String readRequired(final String prompt) {
    String value;

    do {
      out.print(prompt);
      value = scanner.nextLine().trim();

      if (value.isBlank()) {
        out.println("  Value cannot be blank. Please try again.");
      }
    } while (value.isBlank());

    return value;
  }

  public String readOptional(final String prompt) {
    out.print(prompt);
    return scanner.nextLine().trim();
  }

  public int readInt(final String prompt) {
    while (true) {
      out.print(prompt);
      final String raw = scanner.nextLine().trim();

      try {
        return Integer.parseInt(raw);
      } catch (final NumberFormatException ignored) {
        out.println("  Invalid input. Please enter a number.");
      }
    }
  }

  public double readDouble(final String prompt) {
    while (true) {
      out.print(prompt);
      final String raw = scanner.nextLine().trim();

      try {
        return Double.parseDouble(raw);
      } catch (final NumberFormatException ignored) {
        out.println("  Invalid input. Please enter a valid decimal number.");
      }
    }
  }

  public String readPlaca() {
    return readRequired("  Plate: ");
  }

  public String readMarca() {
    return readRequired("  Brand: ");
  }

  public String readModelo() {
    return readRequired("  Model: ");
  }

  public int readVersion() {
    return readInt("  Version: ");
  }

  public String readColor() {
    return readRequired("  Color: ");
  }

  public int readNumPuestos() {
    return readInt("  Number of seats: ");
  }

  public int readNumPuertas() {
    return readInt("  Number of doors: ");
  }

  public double readKilometros() {
    return readDouble("  Mileage: ");
  }

  public int readCilindraje() {
    return readInt("  Engine displacement (cc): ");
  }

  public String readCategoria() {
    return readRequired("  Category: ");
  }

  public String readCombustible() {
    return readRequired("  Fuel type: ");
  }

  // ── Output ────────────────────────────────────────

  public void println(final String message) {
    out.println(message);
  }

  public void println() {
    out.println();
  }

  public void printf(final String format, final Object... args) {
    out.printf(format, args);
  }
}