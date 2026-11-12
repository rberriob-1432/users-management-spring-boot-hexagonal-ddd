package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetVehiculoByPlacaQuery;
import com.jcaa.usersmanagement.domain.enums.VehiculoCategoria;
import com.jcaa.usersmanagement.domain.enums.VehiculoCombustible;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoApplicationMapperTest {

    private CreateVehiculoCommand createCommand() {
        return new CreateVehiculoCommand(
                "ABC123",
                "Toyota",
                "Corolla",
                "XEI",
                "Blanco",
                5,
                4,
                "GASOLINA",
                50000,
                1800,
                "AUTOMOVIL"
        );
    }

    private UpdateVehiculoCommand updateCommand() {
        return new UpdateVehiculoCommand(
                "ABC123",
                "Mazda",
                "CX-5",
                "Grand Touring",
                "Negro",
                5,
                4,
                "DIESEL",
                30000,
                2200,
                "CAMIONETA"
        );
    }

    @Test
    void shouldMapCreateCommandToVehiculoModel() {
        CreateVehiculoCommand command = createCommand();

        VehiculoModel result =
                VehiculoApplicationMapper.fromCreateCommandToModel(command);

        assertNotNull(result);
        assertEquals("ABC123", result.getPlaca().value());
        assertEquals("Toyota", result.getMarca().value());
        assertEquals("Corolla", result.getModelo().value());
        assertEquals("XEI", result.getVersion().value());
        assertEquals("Blanco", result.getColor().value());
        assertEquals(5, result.getNumPuestos().value());
        assertEquals(4, result.getNumPuertas().value());
        assertEquals(VehiculoCombustible.GASOLINA, result.getCombustible());
        assertEquals(50000, result.getKilometros().value());
        assertEquals(1800, result.getCilindraje().value());
        assertEquals(VehiculoCategoria.AUTOMOVIL, result.getCategoria());
    }

    @Test
    void shouldMapUpdateCommandToVehiculoModel() {
        UpdateVehiculoCommand command = updateCommand();

        VehiculoModel result =
                VehiculoApplicationMapper.fromUpdateCommandToModel(command);

        assertNotNull(result);
        assertEquals("ABC123", result.getPlaca().value());
        assertEquals("Mazda", result.getMarca().value());
        assertEquals("CX-5", result.getModelo().value());
        assertEquals("Grand Touring", result.getVersion().value());
        assertEquals("Negro", result.getColor().value());
        assertEquals(5, result.getNumPuestos().value());
        assertEquals(4, result.getNumPuertas().value());
        assertEquals(VehiculoCombustible.DIESEL, result.getCombustible());
        assertEquals(30000, result.getKilometros().value());
        assertEquals(2200, result.getCilindraje().value());
        assertEquals(VehiculoCategoria.CAMIONETA, result.getCategoria());
    }

    @Test
    void shouldMapGetVehiculoByPlacaQueryToVehiculoPlaca() {
        GetVehiculoByPlacaQuery query =
                new GetVehiculoByPlacaQuery("ABC123");

        VehiculoPlaca result =
                VehiculoApplicationMapper
                        .fromGetVehiculoByPlacaQueryToVehiculoPlaca(query);

        assertNotNull(result);
        assertEquals("ABC123", result.value());
    }

    @Test
    void shouldMapDeleteCommandToVehiculoPlaca() {
        DeleteVehiculoCommand command =
                new DeleteVehiculoCommand("ABC123");

        VehiculoPlaca result =
                VehiculoApplicationMapper
                        .fromDeleteCommandToVehiculoPlaca(command);

        assertNotNull(result);
        assertEquals("ABC123", result.value());
    }
}