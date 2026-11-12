package com.jcaa.usersmanagement.domain.event;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoDeletedDomainEventTest {

    @Test
    void shouldCreateDomainEventSuccessfully() {
        VehiculoPlaca placa = new VehiculoPlaca("ABC123");

        VehiculoDeletedDomainEvent event =
                new VehiculoDeletedDomainEvent(placa);

        assertNotNull(event);
        assertEquals("vehiculo.deleted", event.getEventName());
        assertEquals(placa, event.getPlaca());
    }

    @Test
    void shouldReturnCorrectPayload() {
        VehiculoPlaca placa = new VehiculoPlaca("ABC123");

        VehiculoDeletedDomainEvent event =
                new VehiculoDeletedDomainEvent(placa);

        Map<String, String> payload = event.payload();

        assertEquals(1, payload.size());
        assertEquals("ABC123", payload.get("placa"));
    }
}