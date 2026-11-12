package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.valueobject.VehiculoCilindraje;
import com.jcaa.usersmanagement.domain.enums.VehiculoCategoria;
import com.jcaa.usersmanagement.domain.enums.VehiculoCombustible;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.VehiculoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.VehiculoEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class VehiculoPersistenceMapper {

    public VehiculoPersistenceDto fromModelToDto(final VehiculoModel vehiculo) {
        return new VehiculoPersistenceDto(
                vehiculo.getPlaca().value(),
                vehiculo.getMarca().value(),
                vehiculo.getModelo().value(),
                vehiculo.getVersion().value(),
                vehiculo.getColor().value(),
                vehiculo.getNumPuestos().value(),
                vehiculo.getNumPuertas().value(),
                vehiculo.getCombustible().name(),
                vehiculo.getKilometros().value(),
                vehiculo.getCilindraje().value(),
                vehiculo.getCategoria().name()
        );
    }

    public VehiculoEntity fromResultSetToEntity(final ResultSet resultSet)
            throws SQLException {

        return new VehiculoEntity(
                resultSet.getString("placa"),
                resultSet.getString("marca"),
                resultSet.getString("modelo"),
                resultSet.getString("version"),
                resultSet.getString("color"),
                resultSet.getInt("num_puestos"),
                resultSet.getInt("num_puertas"),
                resultSet.getString("combustible"),
                resultSet.getInt("kilometros"),
                resultSet.getInt("cilindraje"),
                resultSet.getString("categoria")
        );
    }

    public VehiculoModel fromEntityToModel(final VehiculoEntity entity) {
        return VehiculoModel.create(
                new VehiculoPlaca(entity.placa()),
                new VehiculoMarca(entity.marca()),
                new VehiculoModelo(entity.modelo()),
                new VehiculoVersion(entity.version()),
                new VehiculoColor(entity.color()),
                new VehiculoNumPuestos(entity.numPuestos()),
                new VehiculoNumPuertas(entity.numPuertas()),
                VehiculoCombustible.fromString(entity.combustible()),
                new VehiculoKilometros(entity.kilometros()),
                new VehiculoCilindraje(entity.cilindraje()),
                VehiculoCategoria.fromString(entity.categoria())
        );
    }

    public VehiculoModel fromResultSetToModel(final ResultSet resultSet)
            throws SQLException {

        return fromEntityToModel(fromResultSetToEntity(resultSet));
    }

    public List<VehiculoModel> fromResultSetToModelList(final ResultSet resultSet)
            throws SQLException {

        final List<VehiculoModel> vehiculos = new ArrayList<>();

        while (resultSet.next()) {
            vehiculos.add(fromResultSetToModel(resultSet));
        }

        return vehiculos;
    }
}