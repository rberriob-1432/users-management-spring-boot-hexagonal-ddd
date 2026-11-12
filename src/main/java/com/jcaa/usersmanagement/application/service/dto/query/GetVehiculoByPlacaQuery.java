package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetVehiculoByPlacaQuery(
        @NotBlank(message = "placa must not be blank")
        String placa
) {
}