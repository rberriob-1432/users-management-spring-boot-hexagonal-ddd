
package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllVehiculosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetVehiculoByPlacaUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateVehiculoUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetVehiculoByPlacaQuery;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateVehiculoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateVehiculoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.VehiculoRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.VehiculoRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/vehiculos")
@RequiredArgsConstructor
@Tag(
        name = "Vehículos",
        description = "Gestión de vehículos: crear, consultar, actualizar y eliminar."
)
public class VehiculoRestController {

    private final CreateVehiculoUseCase createVehiculoUseCase;
    private final UpdateVehiculoUseCase updateVehiculoUseCase;
    private final DeleteVehiculoUseCase deleteVehiculoUseCase;
    private final GetVehiculoByPlacaUseCase getVehiculoByPlacaUseCase;
    private final GetAllVehiculosUseCase getAllVehiculosUseCase;

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Crear vehículo",
            description = "Registra un nuevo vehículo en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehículo creado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos."),
            @ApiResponse(responseCode = "401", description = "No autorizado."),
            @ApiResponse(responseCode = "409", description = "Ya existe un vehículo con la placa proporcionada."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    public VehiculoRestResponse create(
            @Valid @RequestBody final CreateVehiculoRestRequest request) {

        final CreateVehiculoCommand command =
                VehiculoRestMapper.toCreateCommand(request);

        final VehiculoModel vehiculo =
                createVehiculoUseCase.execute(command);

        return VehiculoRestMapper.toResponse(vehiculo);
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Listar todos los vehículos",
            description = "Obtiene la lista de todos los vehículos registrados en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehículos consultados exitosamente."),
            @ApiResponse(responseCode = "401", description = "No autorizado."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    public List<VehiculoRestResponse> getAll() {
        return VehiculoRestMapper.toResponseList(
                getAllVehiculosUseCase.execute()
        );
    }

    @GetMapping("/{placa}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Obtener vehículo por placa",
            description = "Consulta un vehículo registrado utilizando su placa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehículo encontrado exitosamente."),
            @ApiResponse(responseCode = "400", description = "La placa proporcionada no tiene un formato válido."),
            @ApiResponse(responseCode = "401", description = "No autorizado."),
            @ApiResponse(responseCode = "404", description = "No existe ningún vehículo con la placa proporcionada."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    public VehiculoRestResponse getByPlaca(
            @PathVariable
            @NotBlank(message = "placa must not be blank")
            @Size(
                    min = 5,
                    max = 7,
                    message = "placa must have between 5 and 7 characters"
            )
            final String placa) {

        final GetVehiculoByPlacaQuery query =
                VehiculoRestMapper.toGetByPlacaQuery(placa);

        final VehiculoModel vehiculo =
                getVehiculoByPlacaUseCase.execute(query);

        return VehiculoRestMapper.toResponse(vehiculo);
    }

    @PutMapping("/{placa}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Actualizar vehículo",
            description = "Actualiza la información de un vehículo existente utilizando su placa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehículo actualizado exitosamente."),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos."),
            @ApiResponse(responseCode = "401", description = "No autorizado."),
            @ApiResponse(responseCode = "404", description = "No existe ningún vehículo con la placa proporcionada."),
            @ApiResponse(responseCode = "409", description = "Conflicto al actualizar el vehículo."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    public VehiculoRestResponse update(
            @PathVariable
            @NotBlank(message = "placa must not be blank")
            @Size(
                    min = 5,
                    max = 7,
                    message = "placa must have between 5 and 7 characters"
            )
            final String placa,
            @Valid @RequestBody final UpdateVehiculoRestRequest request) {

        final UpdateVehiculoCommand command =
                VehiculoRestMapper.toUpdateCommand(placa, request);

        final VehiculoModel vehiculo =
                updateVehiculoUseCase.execute(command);

        return VehiculoRestMapper.toResponse(vehiculo);
    }

    @DeleteMapping("/{placa}")
    @SecurityRequirement(name = "bearerAuth")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Eliminar vehículo",
            description = "Elimina un vehículo registrado utilizando su placa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehículo eliminado exitosamente."),
            @ApiResponse(responseCode = "400", description = "La placa proporcionada no tiene un formato válido."),
            @ApiResponse(responseCode = "401", description = "No autorizado."),
            @ApiResponse(responseCode = "404", description = "No existe ningún vehículo con la placa proporcionada."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    public void delete(
            @PathVariable
            @NotBlank(message = "placa must not be blank")
            @Size(
                    min = 5,
                    max = 7,
                    message = "placa must have between 5 and 7 characters"
            )
            final String placa) {

        final DeleteVehiculoCommand command =
                VehiculoRestMapper.toDeleteCommand(placa);

        deleteVehiculoUseCase.execute(command);
    }
}
