
package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteVehiculoPort;
import com.jcaa.usersmanagement.application.port.out.FindAllVehiculosPort;
import com.jcaa.usersmanagement.application.port.out.FindVehiculoByPlacaPort;
import com.jcaa.usersmanagement.application.port.out.SaveVehiculoPort;
import com.jcaa.usersmanagement.application.port.out.UpdateVehiculoPort;
import com.jcaa.usersmanagement.domain.exception.VehiculoNotFoundException;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.VehiculoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.VehiculoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class VehiculoRepositoryMySQL implements
        SaveVehiculoPort,
        UpdateVehiculoPort,
        FindVehiculoByPlacaPort,
        FindAllVehiculosPort,
        DeleteVehiculoPort {

    private static final String SQL_INSERT =
            "INSERT INTO vehiculos " +
                    "(placa, marca, modelo, version, color, num_puestos, num_puertas, " +
                    "combustible, kilometros, cilindraje, categoria) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE vehiculos SET " +
                    "marca = ?, modelo = ?, version = ?, color = ?, num_puestos = ?, " +
                    "num_puertas = ?, combustible = ?, kilometros = ?, cilindraje = ?, " +
                    "categoria = ? WHERE placa = ?";

    private static final String SQL_SELECT_BY_PLACA =
            "SELECT placa, marca, modelo, version, color, num_puestos, num_puertas, " +
                    "combustible, kilometros, cilindraje, categoria " +
                    "FROM vehiculos WHERE placa = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT placa, marca, modelo, version, color, num_puestos, num_puertas, " +
                    "combustible, kilometros, cilindraje, categoria " +
                    "FROM vehiculos";

    private static final String SQL_DELETE =
            "DELETE FROM vehiculos WHERE placa = ?";

    private final DataSource dataSource;

    @Override
    public VehiculoModel save(final VehiculoModel vehiculo) {
        final VehiculoPersistenceDto dto =
                VehiculoPersistenceMapper.fromModelToDto(vehiculo);

        executeSave(dto);

        return findByPlacaOrFail(vehiculo.getPlaca());
    }

    @Override
    public VehiculoModel update(final VehiculoModel vehiculo) {
        final VehiculoPersistenceDto dto =
                VehiculoPersistenceMapper.fromModelToDto(vehiculo);

        executeUpdate(dto);

        return findByPlacaOrFail(vehiculo.getPlaca());
    }

    @Override
    public Optional<VehiculoModel> findByPlaca(final VehiculoPlaca placa) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_BY_PLACA)) {

            statement.setString(1, placa.value());

            try (final ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }

                return Optional.of(
                        VehiculoPersistenceMapper.fromResultSetToModel(resultSet)
                );
            }

        } catch (final SQLException exception) {
            throw PersistenceException.becauseVehicleFindByPlacaFailed(
                    placa.value(), exception);
        }
    }

    @Override
    public List<VehiculoModel> findAll() {
        final List<VehiculoModel> vehiculos = new ArrayList<>();

        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_ALL);
             final ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                vehiculos.add(
                        VehiculoPersistenceMapper.fromResultSetToModel(resultSet)
                );
            }

            return vehiculos;

        } catch (final SQLException exception) {
            throw PersistenceException.becauseVehicleFindAllFailed(exception);
        }
    }

    @Override
    public void deleteByPlaca(final VehiculoPlaca placa) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement =
                     connection.prepareStatement(SQL_DELETE)) {

            statement.setString(1, placa.value());

            final int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw VehiculoNotFoundException.becausePlateWasNotFound(
                        placa.value());
            }

        } catch (final SQLException exception) {
            throw PersistenceException.becauseVehicleDeleteFailed(
                    placa.value(), exception);
        }
    }

    private void executeSave(final VehiculoPersistenceDto dto) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement =
                     connection.prepareStatement(SQL_INSERT)) {

            statement.setString(1, dto.placa());
            statement.setString(2, dto.marca());
            statement.setString(3, dto.modelo());
            statement.setString(4, dto.version());
            statement.setString(5, dto.color());
            statement.setInt(6, dto.numPuestos());
            statement.setInt(7, dto.numPuertas());
            statement.setString(8, dto.combustible());
            statement.setInt(9, dto.kilometros());
            statement.setInt(10, dto.cilindraje());
            statement.setString(11, dto.categoria());

            statement.executeUpdate();

        } catch (final SQLException exception) {
            throw PersistenceException.becauseVehicleSaveFailed(
                    dto.placa(), exception);
        }
    }

    private void executeUpdate(final VehiculoPersistenceDto dto) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement =
                     connection.prepareStatement(SQL_UPDATE)) {

            statement.setString(1, dto.marca());
            statement.setString(2, dto.modelo());
            statement.setString(3, dto.version());
            statement.setString(4, dto.color());
            statement.setInt(5, dto.numPuestos());
            statement.setInt(6, dto.numPuertas());
            statement.setString(7, dto.combustible());
            statement.setInt(8, dto.kilometros());
            statement.setInt(9, dto.cilindraje());
            statement.setString(10, dto.categoria());
            statement.setString(11, dto.placa());

            final int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw VehiculoNotFoundException.becausePlateWasNotFound(
                        dto.placa());
            }

        } catch (final SQLException exception) {
            throw PersistenceException.becauseVehicleUpdateFailed(
                    dto.placa(), exception);
        }
    }

    private VehiculoModel findByPlacaOrFail(final VehiculoPlaca placa) {
        return findByPlaca(placa)
                .orElseThrow(() -> VehiculoNotFoundException
                        .becausePlateWasNotFound(placa.value()));
    }
}
