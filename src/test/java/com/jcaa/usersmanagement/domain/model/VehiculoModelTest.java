package com.jcaa.usersmanagement.domain.model;
import com.jcaa.usersmanagement.domain.enums.VehiculoCategoria;
import com.jcaa.usersmanagement.domain.enums.VehiculoCombustible;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import com.jcaa.usersmanagement.domain.valueobject.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoModelTest {

    @Test
    void shouldCreateVehiculoModelSuccessfully() {

        VehiculoModel vehiculo = createVehiculo();

        assertNotNull(vehiculo);
    }

    @Test
    void shouldAssignAllAttributesCorrectly() {

        VehiculoModel vehiculo = createVehiculo();

        assertEquals("ABC123", vehiculo.getPlaca().value());
        assertEquals("Toyota", vehiculo.getMarca().value());
        assertEquals("Corolla", vehiculo.getModelo().value());
        assertEquals("SE", vehiculo.getVersion().value());
        assertEquals("Rojo", vehiculo.getColor().value());
        assertEquals(5, vehiculo.getNumPuestos().value());
        assertEquals(4, vehiculo.getNumPuertas().value());
        assertEquals(VehiculoCombustible.GASOLINA, vehiculo.getCombustible());
        assertEquals(50000, vehiculo.getKilometros().value());
        assertEquals(1800, vehiculo.getCilindraje().value());
        assertEquals(VehiculoCategoria.AUTOMOVIL, vehiculo.getCategoria());
    }

    @Test
    void shouldGenerateEqualObjectsWithSameAttributes() {

        VehiculoModel first = createVehiculo();
        VehiculoModel second = createVehiculo();

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenAttributesAreDifferent() {

        VehiculoModel first = createVehiculo();

        VehiculoModel second = VehiculoModel.create(
                new VehiculoPlaca("XYZ789"),
                new VehiculoMarca("Mazda"),
                new VehiculoModelo("CX5"),
                new VehiculoVersion("Touring"),
                new VehiculoColor("Negro"),
                new VehiculoNumPuestos(5),
                new VehiculoNumPuertas(4),
                VehiculoCombustible.DIESEL,
                new VehiculoKilometros(30000),
                new VehiculoCilindraje(2000),
                VehiculoCategoria.CAMIONETA
        );
        assertNotEquals(first, second);
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