
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.out.FindVehiculoByPlacaPort;
import com.jcaa.usersmanagement.application.port.out.UpdateVehiculoPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.mapper.VehiculoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.VehiculoNotFoundException;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateVehiculoService implements UpdateVehiculoUseCase {

    private final UpdateVehiculoPort updateVehiculoPort;
    private final FindVehiculoByPlacaPort findVehiculoByPlacaPort;
    private final Validator validator;

    @Override
    public VehiculoModel execute(final UpdateVehiculoCommand command) {

        validateCommand(command);

        final VehiculoPlaca placa = new VehiculoPlaca(command.placa());

        ensureVehiculoExists(placa);

        final VehiculoModel vehiculo =
                VehiculoApplicationMapper.fromUpdateCommandToModel(command);

        final VehiculoModel updatedVehiculo =
                updateVehiculoPort.update(vehiculo);

        log.info("Vehicle updated successfully with plate: {}", placa.value());

        return updatedVehiculo;
    }

    private void validateCommand(final UpdateVehiculoCommand command) {

        final Set<ConstraintViolation<UpdateVehiculoCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureVehiculoExists(final VehiculoPlaca placa) {

        findVehiculoByPlacaPort.findByPlaca(placa)
                .orElseThrow(() -> VehiculoNotFoundException
                        .becausePlateWasNotFound(placa.value()));
    }
}
