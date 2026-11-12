package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetVehiculoByPlacaQuery;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateVehiculoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateVehiculoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.VehiculoResponse;

import java.util.List;

public final class VehiculoDesktopMapper {

    private VehiculoDesktopMapper() {
    }

    public static CreateVehiculoCommand toCreateCommand(
            final CreateVehiculoRequest request) {

        return new CreateVehiculoCommand(
                request.placa(),
                request.marca(),
                request.modelo(),
                request.version(),
                request.color(),
                request.numPuestos(),
                request.numPuertas(),
                request.combustible(),
                request.kilometros(),
                request.cilindraje(),
                request.categoria()
        );
    }

    public static UpdateVehiculoCommand toUpdateCommand(
            final UpdateVehiculoRequest request) {

        return new UpdateVehiculoCommand(
                request.placa(),
                request.marca(),
                request.modelo(),
                request.version(),
                request.color(),
                request.numPuestos(),
                request.numPuertas(),
                request.combustible(),
                request.kilometros(),
                request.cilindraje(),
                request.categoria()
        );
    }

    public static DeleteVehiculoCommand toDeleteCommand(final String placa) {
        return new DeleteVehiculoCommand(placa);
    }

    public static GetVehiculoByPlacaQuery toGetByPlacaQuery(final String placa) {
        return new GetVehiculoByPlacaQuery(placa);
    }

    public static VehiculoResponse toResponse(final VehiculoModel vehiculo) {
        return new VehiculoResponse(
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

    public static List<VehiculoResponse> toResponseList(
            final List<VehiculoModel> vehiculos) {

        return vehiculos.stream()
                .map(VehiculoDesktopMapper::toResponse)
                .toList();
    }
}
