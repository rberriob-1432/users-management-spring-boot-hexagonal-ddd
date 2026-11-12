package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.VehiculoCategoria;
import com.jcaa.usersmanagement.domain.enums.VehiculoCombustible;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.VehiculoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.VehiculoEntity;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehiculoPersistenceMapperTest {

    private VehiculoEntity createEntity() {
        return new VehiculoEntity(
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

    @Test
    void shouldMapVehiculoModelToPersistenceDto() {
        VehiculoModel model =
                VehiculoPersistenceMapper.fromEntityToModel(createEntity());

        VehiculoPersistenceDto result =
                VehiculoPersistenceMapper.fromModelToDto(model);

        assertNotNull(result);
        assertEquals("ABC123", result.placa());
        assertEquals("Toyota", result.marca());
        assertEquals("Corolla", result.modelo());
        assertEquals("XEI", result.version());
        assertEquals("Blanco", result.color());
        assertEquals(5, result.numPuestos());
        assertEquals(4, result.numPuertas());
        assertEquals("GASOLINA", result.combustible());
        assertEquals(50000, result.kilometros());
        assertEquals(1800, result.cilindraje());
        assertEquals("AUTOMOVIL", result.categoria());
    }

    @Test
    void shouldMapResultSetToVehiculoEntity() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getString("placa")).thenReturn("ABC123");
        when(resultSet.getString("marca")).thenReturn("Toyota");
        when(resultSet.getString("modelo")).thenReturn("Corolla");
        when(resultSet.getString("version")).thenReturn("XEI");
        when(resultSet.getString("color")).thenReturn("Blanco");
        when(resultSet.getInt("num_puestos")).thenReturn(5);
        when(resultSet.getInt("num_puertas")).thenReturn(4);
        when(resultSet.getString("combustible")).thenReturn("GASOLINA");
        when(resultSet.getInt("kilometros")).thenReturn(50000);
        when(resultSet.getInt("cilindraje")).thenReturn(1800);
        when(resultSet.getString("categoria")).thenReturn("AUTOMOVIL");

        VehiculoEntity result =
                VehiculoPersistenceMapper.fromResultSetToEntity(resultSet);

        assertNotNull(result);
        assertEquals("ABC123", result.placa());
        assertEquals("Toyota", result.marca());
        assertEquals("Corolla", result.modelo());
        assertEquals("XEI", result.version());
        assertEquals("Blanco", result.color());
        assertEquals(5, result.numPuestos());
        assertEquals(4, result.numPuertas());
        assertEquals("GASOLINA", result.combustible());
        assertEquals(50000, result.kilometros());
        assertEquals(1800, result.cilindraje());
        assertEquals("AUTOMOVIL", result.categoria());
    }

    @Test
    void shouldMapVehiculoEntityToModel() {
        VehiculoModel result =
                VehiculoPersistenceMapper.fromEntityToModel(createEntity());

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
    void shouldMapResultSetToVehiculoModel() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getString("placa")).thenReturn("ABC123");
        when(resultSet.getString("marca")).thenReturn("Toyota");
        when(resultSet.getString("modelo")).thenReturn("Corolla");
        when(resultSet.getString("version")).thenReturn("XEI");
        when(resultSet.getString("color")).thenReturn("Blanco");
        when(resultSet.getInt("num_puestos")).thenReturn(5);
        when(resultSet.getInt("num_puertas")).thenReturn(4);
        when(resultSet.getString("combustible")).thenReturn("GASOLINA");
        when(resultSet.getInt("kilometros")).thenReturn(50000);
        when(resultSet.getInt("cilindraje")).thenReturn(1800);
        when(resultSet.getString("categoria")).thenReturn("AUTOMOVIL");

        VehiculoModel result =
                VehiculoPersistenceMapper.fromResultSetToModel(resultSet);

        assertNotNull(result);
        assertEquals("ABC123", result.getPlaca().value());
        assertEquals(VehiculoCategoria.AUTOMOVIL, result.getCategoria());
        assertEquals(VehiculoCombustible.GASOLINA, result.getCombustible());
    }

    @Test
    void shouldMapResultSetToVehiculoModelList() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.next()).thenReturn(true, true, false);

        when(resultSet.getString("placa"))
                .thenReturn("ABC123", "XYZ789");
        when(resultSet.getString("marca"))
                .thenReturn("Toyota", "Mazda");
        when(resultSet.getString("modelo"))
                .thenReturn("Corolla", "CX-5");
        when(resultSet.getString("version"))
                .thenReturn("XEI", "Grand Touring");
        when(resultSet.getString("color"))
                .thenReturn("Blanco", "Negro");
        when(resultSet.getInt("num_puestos")).thenReturn(5, 5);
        when(resultSet.getInt("num_puertas")).thenReturn(4, 4);
        when(resultSet.getString("combustible"))
                .thenReturn("GASOLINA", "DIESEL");
        when(resultSet.getInt("kilometros")).thenReturn(50000, 30000);
        when(resultSet.getInt("cilindraje")).thenReturn(1800, 2200);
        when(resultSet.getString("categoria"))
                .thenReturn("AUTOMOVIL", "CAMIONETA");

        List<VehiculoModel> result =
                VehiculoPersistenceMapper.fromResultSetToModelList(resultSet);

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("ABC123", result.get(0).getPlaca().value());
        assertEquals("XYZ789", result.get(1).getPlaca().value());

        assertEquals(VehiculoCategoria.AUTOMOVIL, result.get(0).getCategoria());
        assertEquals(VehiculoCategoria.CAMIONETA, result.get(1).getCategoria());

        verify(resultSet, times(3)).next();
    }

    @Test
    void shouldReturnEmptyListWhenResultSetHasNoRecords() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.next()).thenReturn(false);

        List<VehiculoModel> result =
                VehiculoPersistenceMapper.fromResultSetToModelList(resultSet);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(resultSet).next();
        verify(resultSet, never()).getString(anyString());
    }
}