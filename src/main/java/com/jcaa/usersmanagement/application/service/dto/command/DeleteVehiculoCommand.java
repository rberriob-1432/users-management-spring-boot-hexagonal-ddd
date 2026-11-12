package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DeleteVehiculoCommand(

        @NotBlank(message = "placa must not be blank")
        @Size(min = 5, max = 7, message = "placa must have between 5 and 7 characters")
        String placa

) {
}