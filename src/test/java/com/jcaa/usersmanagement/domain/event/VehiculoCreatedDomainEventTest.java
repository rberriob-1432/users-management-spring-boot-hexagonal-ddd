package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.enums.VehiculoCategoria;
import com.jcaa.usersmanagement.domain.enums.VehiculoCombustible;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoCilindraje;

import com.jcaa.usersmanagement.domain.valueobject.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoCreatedDomainEventTest {

    @Test
    void shouldCreateDomainEventSuccessfully() {
        VehiculoModel vehiculo = createVehiculo();

        VehiculoCreatedDomainEvent event =
                new VehiculoCreatedDomainEvent(vehiculo);

        assertNotNull(event);
        assertEquals("vehiculo.created", event.getEventName());
        assertEquals(vehiculo, event.getVehiculo());
    }

    @Test
    void shouldReturnCorrectPayload() {
        VehiculoModel vehiculo = createVehiculo();

        VehiculoCreatedDomainEvent event =
                new VehiculoCreatedDomainEvent(vehiculo);

        Map<String, String> payload = event.payload();

        assertEquals(11, payload.size());
        assertEquals("ABC123", payload.get("placa"));
        assertEquals("Toyota", payload.get("marca"));
        assertEquals("Corolla", payload.get("modelo"));
        assertEquals("SE", payload.get("version"));
        assertEquals("Rojo", payload.get("color"));
        assertEquals("5", payload.get("numPuestos"));
        assertEquals("4", payload.get("numPuertas"));
        assertEquals("GASOLINA", payload.get("combustible"));
        assertEquals("50000", payload.get("kilometros"));
        assertEquals("1800", payload.get("cilindraje"));
        assertEquals("AUTOMOVIL", payload.get("categoria"));
    }

    private VehiculoModel createVehiculo() {
        return VehiculoModel.create(
                new VehiculoPlaca("ABC123"),
                new VehiculoMarca("Toyota"),
                new VehiculoModelo("Corolla"),
                new VehiculoVersion("SE"),
                new VehiculoColor("Rojo"),
                new VehiculoNumPuestos(5),
                new VehiculoNumPuertas(4),
                VehiculoCombustible.GASOLINA,
                new VehiculoKilometros(50000),
                new VehiculoCilindraje(1800),
                VehiculoCategoria.AUTOMOVIL
        );
    }
}