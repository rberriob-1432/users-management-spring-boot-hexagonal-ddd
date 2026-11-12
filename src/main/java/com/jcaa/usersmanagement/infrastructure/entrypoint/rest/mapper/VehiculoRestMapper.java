package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetVehiculoByPlacaQuery;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateVehiculoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateVehiculoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.VehiculoRestResponse;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VehiculoRestMapper {

    public CreateVehiculoCommand toCreateCommand(
            final CreateVehiculoRestRequest request) {

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

    public UpdateVehiculoCommand toUpdateCommand(
            final String placa,
            final UpdateVehiculoRestRequest request) {

        return new UpdateVehiculoCommand(
                placa,
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

    public GetVehiculoByPlacaQuery toGetByPlacaQuery(final String placa) {
        return new GetVehiculoByPlacaQuery(placa);
    }

    public DeleteVehiculoCommand toDeleteCommand(final String placa) {
        return new DeleteVehiculoCommand(placa);
    }

    public VehiculoRestResponse toResponse(final VehiculoModel vehiculo) {
        return new VehiculoRestResponse(
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

    public List<VehiculoRestResponse> toResponseList(
            final List<VehiculoModel> vehiculos) {

        return vehiculos.stream()
                .map(VehiculoRestMapper::toResponse)
                .toList();
    }
}
