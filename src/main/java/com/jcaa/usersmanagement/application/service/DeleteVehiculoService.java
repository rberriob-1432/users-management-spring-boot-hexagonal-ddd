
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteVehiculoPort;
import com.jcaa.usersmanagement.application.port.out.FindVehiculoByPlacaPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteVehiculoCommand;
import com.jcaa.usersmanagement.application.service.mapper.VehiculoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.VehiculoNotFoundException;
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
public class DeleteVehiculoService implements DeleteVehiculoUseCase {

    private final DeleteVehiculoPort deleteVehiculoPort;
    private final FindVehiculoByPlacaPort findVehiculoByPlacaPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteVehiculoCommand command) {
        validateCommand(command);

        final VehiculoPlaca placa =
                VehiculoApplicationMapper.fromDeleteCommandToVehiculoPlaca(command);

        ensureVehiculoExists(placa);

        deleteVehiculoPort.deleteByPlaca(placa);

        log.info("Vehicle deleted successfully with plate: {}", placa.value());
    }

    private void validateCommand(final DeleteVehiculoCommand command) {
        final Set<ConstraintViolation<DeleteVehiculoCommand>> violations =
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
