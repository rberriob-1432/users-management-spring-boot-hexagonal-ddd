package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllVehiculosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetVehiculoByPlacaUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateVehiculoUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateVehiculoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateVehiculoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.VehiculoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.VehiculoDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class VehiculoController {

    private final CreateVehiculoUseCase createVehiculoUseCase;
    private final UpdateVehiculoUseCase updateVehiculoUseCase;
    private final DeleteVehiculoUseCase deleteVehiculoUseCase;
    private final GetVehiculoByPlacaUseCase getVehiculoByPlacaUseCase;
    private final GetAllVehiculosUseCase getAllVehiculosUseCase;

    public List<VehiculoResponse> listAllVehiculos() {
        final var vehiculos = getAllVehiculosUseCase.execute();
        return VehiculoDesktopMapper.toResponseList(vehiculos);
    }

    public VehiculoResponse findVehiculoByPlaca(final String placa) {
        final var query = VehiculoDesktopMapper.toGetByPlacaQuery(placa);
        final var vehiculo = getVehiculoByPlacaUseCase.execute(query);
        return VehiculoDesktopMapper.toResponse(vehiculo);
    }

    public VehiculoResponse createVehiculo(final CreateVehiculoRequest request) {
        final var command = VehiculoDesktopMapper.toCreateCommand(request);
        final var vehiculo = createVehiculoUseCase.execute(command);
        return VehiculoDesktopMapper.toResponse(vehiculo);
    }

    public VehiculoResponse updateVehiculo(final UpdateVehiculoRequest request) {
        final var command = VehiculoDesktopMapper.toUpdateCommand(request);
        final var vehiculo = updateVehiculoUseCase.execute(command);
        return VehiculoDesktopMapper.toResponse(vehiculo);
    }

    public void deleteVehiculo(final String placa) {
        final var command = VehiculoDesktopMapper.toDeleteCommand(placa);
        deleteVehiculoUseCase.execute(command);
    }
}
