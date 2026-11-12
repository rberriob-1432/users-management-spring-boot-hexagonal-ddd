package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteVehiculoCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteVehiculoUseCase {

    void execute(@NotNull @Valid DeleteVehiculoCommand command);
}