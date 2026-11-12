package com.jcaa.usersmanagement.application.service.mapper;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoCilindraje;
import com.jcaa.usersmanagement.application.service.dto.command.CreateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetVehiculoByPlacaQuery;
import com.jcaa.usersmanagement.domain.enums.VehiculoCategoria;
import com.jcaa.usersmanagement.domain.enums.VehiculoCombustible;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VehiculoApplicationMapper {

    public VehiculoModel fromCreateCommandToModel(
            final CreateVehiculoCommand command) {

        return VehiculoModel.create(
                new VehiculoPlaca(command.placa()),
                new VehiculoMarca(command.marca()),
                new VehiculoModelo(command.modelo()),
                new VehiculoVersion(command.version()),
                new VehiculoColor(command.color()),
                new VehiculoNumPuestos(command.numPuestos()),
                new VehiculoNumPuertas(command.numPuertas()),
                VehiculoCombustible.fromString(command.combustible()),
                new VehiculoKilometros(command.kilometros()),
                new VehiculoCilindraje(command.cilindraje()),
                VehiculoCategoria.fromString(command.categoria())
        );
    }

    public VehiculoModel fromUpdateCommandToModel(
            final UpdateVehiculoCommand command) {

        return VehiculoModel.create(
                new VehiculoPlaca(command.placa()),
                new VehiculoMarca(command.marca()),
                new VehiculoModelo(command.modelo()),
                new VehiculoVersion(command.version()),
                new VehiculoColor(command.color()),
                new VehiculoNumPuestos(command.numPuestos()),
                new VehiculoNumPuertas(command.numPuertas()),
                VehiculoCombustible.fromString(command.combustible()),
                new VehiculoKilometros(command.kilometros()),
                new VehiculoCilindraje(command.cilindraje()),
                VehiculoCategoria.fromString(command.categoria())
        );
    }

    public VehiculoPlaca fromGetVehiculoByPlacaQueryToVehiculoPlaca(
            final GetVehiculoByPlacaQuery query) {

        return new VehiculoPlaca(query.placa());
    }

    public VehiculoPlaca fromDeleteCommandToVehiculoPlaca(
            final DeleteVehiculoCommand command) {

        return new VehiculoPlaca(command.placa());
    }
}