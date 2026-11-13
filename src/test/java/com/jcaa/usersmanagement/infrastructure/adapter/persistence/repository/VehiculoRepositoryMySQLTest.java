package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.domain.exception.VehiculoNotFoundException;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.VehiculoEntity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.VehiculoPersistenceMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehiculoRepositoryMySQLTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement statement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private VehiculoRepositoryMySQL repository;

    private VehiculoModel createVehiculo() {
        VehiculoEntity entity = new VehiculoEntity(
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

        return VehiculoPersistenceMapper.fromEntityToModel(entity);
    }

    private void stubResultSet(ResultSet resultSet) throws SQLException {
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
    }

    @Test
    void shouldSaveVehiculoSuccessfully() throws SQLException {
        VehiculoModel vehiculo = createVehiculo();

        Connection insertConnection = mock(Connection.class);
        Connection selectConnection = mock(Connection.class);

        PreparedStatement insertStatement = mock(PreparedStatement.class);
        PreparedStatement selectStatement = mock(PreparedStatement.class);

        ResultSet selectResultSet = mock(ResultSet.class);

        when(dataSource.getConnection())
                .thenReturn(insertConnection, selectConnection);

        when(insertConnection.prepareStatement(anyString()))
                .thenReturn(insertStatement);

        when(selectConnection.prepareStatement(anyString()))
                .thenReturn(selectStatement);

        when(insertStatement.executeUpdate()).thenReturn(1);

        when(selectStatement.executeQuery()).thenReturn(selectResultSet);
        when(selectResultSet.next()).thenReturn(true);

        stubResultSet(selectResultSet);

        VehiculoModel result = repository.save(vehiculo);

        assertNotNull(result);
        assertEquals("ABC123", result.getPlaca().value());
        assertEquals("Toyota", result.getMarca().value());

        verify(insertStatement).setString(1, "ABC123");
        verify(insertStatement).setString(2, "Toyota");
        verify(insertStatement).setString(3, "Corolla");
        verify(insertStatement).setString(4, "XEI");
        verify(insertStatement).setString(5, "Blanco");
        verify(insertStatement).setInt(6, 5);
        verify(insertStatement).setInt(7, 4);
        verify(insertStatement).setString(8, "GASOLINA");
        verify(insertStatement).setInt(9, 50000);
        verify(insertStatement).setInt(10, 1800);
        verify(insertStatement).setString(11, "AUTOMOVIL");

        verify(insertStatement).executeUpdate();
        verify(selectStatement).executeQuery();
    }

    @Test
    void shouldUpdateVehiculoSuccessfully() throws SQLException {
        VehiculoModel vehiculo = createVehiculo();

        Connection updateConnection = mock(Connection.class);
        Connection selectConnection = mock(Connection.class);

        PreparedStatement updateStatement = mock(PreparedStatement.class);
        PreparedStatement selectStatement = mock(PreparedStatement.class);

        ResultSet selectResultSet = mock(ResultSet.class);

        when(dataSource.getConnection())
                .thenReturn(updateConnection, selectConnection);

        when(updateConnection.prepareStatement(anyString()))
                .thenReturn(updateStatement);

        when(selectConnection.prepareStatement(anyString()))
                .thenReturn(selectStatement);

        // El UPDATE debe afectar una fila.
        when(updateStatement.executeUpdate()).thenReturn(1);

        // La consulta posterior debe encontrar el vehículo.
        when(selectStatement.executeQuery()).thenReturn(selectResultSet);
        when(selectResultSet.next()).thenReturn(true);

        stubResultSet(selectResultSet);

        VehiculoModel result = repository.update(vehiculo);

        assertNotNull(result);
        assertEquals("ABC123", result.getPlaca().value());
        assertEquals("Toyota", result.getMarca().value());

        verify(updateStatement).setString(1, "Toyota");
        verify(updateStatement).setString(2, "Corolla");
        verify(updateStatement).setString(3, "XEI");
        verify(updateStatement).setString(4, "Blanco");
        verify(updateStatement).setInt(5, 5);
        verify(updateStatement).setInt(6, 4);
        verify(updateStatement).setString(7, "GASOLINA");
        verify(updateStatement).setInt(8, 50000);
        verify(updateStatement).setInt(9, 1800);
        verify(updateStatement).setString(10, "AUTOMOVIL");
        verify(updateStatement).setString(11, "ABC123");

        verify(updateStatement).executeUpdate();
        verify(selectStatement).executeQuery();
    }

    @Test
    void shouldReturnVehiculoWhenFoundByPlaca() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);

        stubResultSet(resultSet);

        Optional<VehiculoModel> result =
                repository.findByPlaca(new VehiculoPlaca("ABC123"));

        assertTrue(result.isPresent());
        assertEquals("ABC123", result.get().getPlaca().value());
        assertEquals("Toyota", result.get().getMarca().value());

        verify(statement).setString(1, "ABC123");
        verify(statement).executeQuery();
    }

    @Test
    void shouldReturnEmptyWhenVehiculoDoesNotExist() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        Optional<VehiculoModel> result =
                repository.findByPlaca(new VehiculoPlaca("XYZ999"));

        assertTrue(result.isEmpty());

        verify(statement).setString(1, "XYZ999");
        verify(statement).executeQuery();
        verify(resultSet, never()).getString(anyString());
    }

    @Test
    void shouldThrowPersistenceExceptionWhenFindByPlacaFails()
            throws SQLException {

        when(dataSource.getConnection())
                .thenThrow(new SQLException("Database connection error"));

        assertThrows(
                PersistenceException.class,
                () -> repository.findByPlaca(new VehiculoPlaca("ABC123"))
        );
    }

    @Test
    void shouldReturnAllVehiculos() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true, true, false);

        stubResultSet(resultSet);

        List<VehiculoModel> result = repository.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("ABC123", result.get(0).getPlaca().value());
        assertEquals("ABC123", result.get(1).getPlaca().value());

        verify(statement).executeQuery();
        verify(resultSet, times(3)).next();
    }

    @Test
    void shouldReturnEmptyListWhenNoVehiculosExist() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        List<VehiculoModel> result = repository.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(resultSet).next();
        verify(resultSet, never()).getString(anyString());
    }

    @Test
    void shouldThrowPersistenceExceptionWhenFindAllFails()
            throws SQLException {

        when(dataSource.getConnection())
                .thenThrow(new SQLException("Database connection error"));

        assertThrows(
                PersistenceException.class,
                () -> repository.findAll()
        );
    }

    @Test
    void shouldDeleteVehiculoSuccessfully() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(
                () -> repository.deleteByPlaca(new VehiculoPlaca("ABC123"))
        );

        verify(statement).setString(1, "ABC123");
        verify(statement).executeUpdate();
    }

    @Test
    void shouldThrowVehiculoNotFoundExceptionWhenDeletingNonExistingVehiculo()
            throws SQLException {

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(0);

        assertThrows(
                VehiculoNotFoundException.class,
                () -> repository.deleteByPlaca(new VehiculoPlaca("XYZ999"))
        );

        verify(statement).setString(1, "XYZ999");
        verify(statement).executeUpdate();
    }

    @Test
    void shouldThrowPersistenceExceptionWhenDeleteFails()
            throws SQLException {

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        when(statement.executeUpdate())
                .thenThrow(new SQLException("Delete failed"));

        assertThrows(
                PersistenceException.class,
                () -> repository.deleteByPlaca(new VehiculoPlaca("ABC123"))
        );
    }

    @Test
    void shouldThrowPersistenceExceptionWhenSaveFails()
            throws SQLException {

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        when(statement.executeUpdate())
                .thenThrow(new SQLException("Insert failed"));

        assertThrows(
                PersistenceException.class,
                () -> repository.save(createVehiculo())
        );

        verify(statement).executeUpdate();
    }

    @Test
    void shouldThrowVehiculoNotFoundExceptionWhenUpdateAffectsNoRows()
            throws SQLException {

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(0);

        assertThrows(
                VehiculoNotFoundException.class,
                () -> repository.update(createVehiculo())
        );

        verify(statement).executeUpdate();
    }

    @Test
    void shouldThrowPersistenceExceptionWhenUpdateFails()
            throws SQLException {

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        when(statement.executeUpdate())
                .thenThrow(new SQLException("Update failed"));

        assertThrows(
                PersistenceException.class,
                () -> repository.update(createVehiculo())
        );

        verify(statement).executeUpdate();
    }
}
