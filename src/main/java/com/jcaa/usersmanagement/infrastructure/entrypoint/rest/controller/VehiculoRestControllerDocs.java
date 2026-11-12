package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateVehiculoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateVehiculoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.ApiErrorResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.VehiculoRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Vehículos",
        description = "Gestión de vehículos: registrar, consultar, actualizar y eliminar.")
public interface VehiculoRestControllerDocs {
    // POST /api/v1/vehiculos

    @Operation(
            summary = "Registrar vehículo",
            description =
                    "Registra un nuevo vehículo. La placa debe ser única y todos los datos "
                            + "deben cumplir las validaciones establecidas.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Vehículo registrado exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VehiculoRestResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los datos proporcionados son inválidos.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Ya existe un vehículo registrado con esa placa.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    VehiculoRestResponse create(@Valid @RequestBody CreateVehiculoRestRequest request);

    // GET /api/v1/vehiculos

    @Operation(
            summary = "Listar vehículos",
            description = "Retorna todos los vehículos registrados. La lista puede estar vacía.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de vehículos obtenida exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            array =
                            @ArraySchema(
                                    schema = @Schema(implementation = VehiculoRestResponse.class)))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    List<VehiculoRestResponse> getAll();

    // GET /api/v1/vehiculos/{placa}

    @Operation(
            summary = "Consultar vehículo por placa",
            description = "Obtiene los datos de un vehículo identificado por su placa.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Vehículo encontrado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VehiculoRestResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "La placa proporcionada no cumple el formato requerido.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe un vehículo con la placa proporcionada.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    VehiculoRestResponse getByPlaca(
            @Parameter(
                    description = "Placa única del vehículo.",
                    example = "ABC123",
                    required = true)
            @PathVariable
            String placa);

    // PUT /api/v1/vehiculos/{placa}

    @Operation(
            summary = "Actualizar vehículo",
            description = "Actualiza los datos de un vehículo existente identificado por su placa.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Vehículo actualizado exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VehiculoRestResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los datos proporcionados son inválidos.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe un vehículo con la placa proporcionada.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    VehiculoRestResponse update(
            @Parameter(
                    description = "Placa del vehículo que se actualizará.",
                    example = "ABC123",
                    required = true)
            @PathVariable
            String placa,
            @Valid @RequestBody UpdateVehiculoRestRequest request);

    // DELETE /api/v1/vehiculos/{placa}

    @Operation(
            summary = "Eliminar vehículo",
            description = "Elimina permanentemente un vehículo mediante su placa.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Vehículo eliminado exitosamente."),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe un vehículo con la placa proporcionada.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    void delete(
            @Parameter(
                    description = "Placa del vehículo que se eliminará.",
                    example = "ABC123",
                    required = true)
            @PathVariable
            String placa);
}