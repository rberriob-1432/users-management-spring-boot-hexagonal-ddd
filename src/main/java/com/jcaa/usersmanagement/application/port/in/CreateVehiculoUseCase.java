package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateVehiculoCommand;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateVehiculoUseCase {

    VehiculoModel execute(@NotNull @Valid CreateVehiculoCommand command);
}