package com.jcaa.usersmanagement.domain.event;

import java.util.Map;

import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;
import lombok.Getter;

@Getter
public final class VehiculoDeletedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "vehiculo.deleted";

    private final VehiculoPlaca placa;

    public VehiculoDeletedDomainEvent(final VehiculoPlaca placa) {
        super(EVENT_NAME);
        this.placa = placa;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of("placa", placa.value());
    }
}