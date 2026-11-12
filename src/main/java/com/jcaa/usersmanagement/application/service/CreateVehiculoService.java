package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.out.FindVehiculoByPlacaPort;
import com.jcaa.usersmanagement.application.port.out.SaveVehiculoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateVehiculoCommand;
import com.jcaa.usersmanagement.application.service.mapper.VehiculoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.VehiculoAlreadyExistsException;
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
public class CreateVehiculoService implements CreateVehiculoUseCase {

    private final SaveVehiculoPort saveVehiculoPort;
    private final FindVehiculoByPlacaPort findVehiculoByPlacaPort;
    private final Validator validator;

    @Override
    public VehiculoModel execute(final CreateVehiculoCommand command) {

        validateCommand(command);

        final VehiculoPlaca placa = new VehiculoPlaca(command.placa());

        ensureVehiculoDoesNotExist(placa);

        final VehiculoModel vehiculo =
                VehiculoApplicationMapper.fromCreateCommandToModel(command);

        final VehiculoModel savedVehiculo = saveVehiculoPort.save(vehiculo);

        log.info("Vehicle created successfully with plate: {}", placa.value());

        return savedVehiculo;
    }

    private void validateCommand(final CreateVehiculoCommand command) {

        final Set<ConstraintViolation<CreateVehiculoCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureVehiculoDoesNotExist(final VehiculoPlaca placa) {

        findVehiculoByPlacaPort.findByPlaca(placa)
                .ifPresent(ignored -> {
                    throw VehiculoAlreadyExistsException
                            .becausePlateAlreadyExists(placa.value());
                });
    }
}
