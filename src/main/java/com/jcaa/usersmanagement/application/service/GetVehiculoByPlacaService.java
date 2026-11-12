
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetVehiculoByPlacaUseCase;
import com.jcaa.usersmanagement.application.port.out.FindVehiculoByPlacaPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetVehiculoByPlacaQuery;
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
public class GetVehiculoByPlacaService implements GetVehiculoByPlacaUseCase {

    private final FindVehiculoByPlacaPort findVehiculoByPlacaPort;
    private final Validator validator;

    @Override
    public VehiculoModel execute(final GetVehiculoByPlacaQuery query) {
        validateQuery(query);

        final VehiculoPlaca placa =
                VehiculoApplicationMapper
                        .fromGetVehiculoByPlacaQueryToVehiculoPlaca(query);

        final VehiculoModel vehiculo = findVehiculoByPlacaPort.findByPlaca(placa)
                .orElseThrow(() -> VehiculoNotFoundException
                        .becausePlateWasNotFound(placa.value()));

        log.info("Vehicle found successfully with plate: {}", placa.value());

        return vehiculo;
    }

    private void validateQuery(final GetVehiculoByPlacaQuery query) {
        final Set<ConstraintViolation<GetVehiculoByPlacaQuery>> violations =
                validator.validate(query);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
