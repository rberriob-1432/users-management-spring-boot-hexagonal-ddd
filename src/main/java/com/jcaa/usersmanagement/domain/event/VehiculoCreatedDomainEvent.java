package com.jcaa.usersmanagement.domain.event;

import java.util.Map;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import lombok.Getter;

@Getter
public final class VehiculoCreatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "vehiculo.created";

    private final VehiculoModel vehiculo;

    public VehiculoCreatedDomainEvent(final VehiculoModel vehiculo) {
        super(EVENT_NAME);
        this.vehiculo = vehiculo;
    }

    @Override
    public Map<String, String> payload() {
        return Map.ofEntries(
                Map.entry("placa", vehiculo.getPlaca().value()),
                Map.entry("marca", vehiculo.getMarca().value()),
                Map.entry("modelo", vehiculo.getModelo().value()),
                Map.entry("version", vehiculo.getVersion().value()),
                Map.entry("color", vehiculo.getColor().value()),
                Map.entry("numPuestos", String.valueOf(vehiculo.getNumPuestos().value())),
                Map.entry("numPuertas", String.valueOf(vehiculo.getNumPuertas().value())),
                Map.entry("combustible", vehiculo.getCombustible().name()),
                Map.entry("kilometros", String.valueOf(vehiculo.getKilometros().value())),
                Map.entry("cilindraje", String.valueOf(vehiculo.getCilindraje().value())),
                Map.entry("categoria", vehiculo.getCategoria().name())
        );
    }
}