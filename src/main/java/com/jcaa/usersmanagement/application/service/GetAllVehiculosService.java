package com.jcaa.usersmanagement.application.service;
import java.util.List;

import com.jcaa.usersmanagement.application.port.in.GetAllVehiculosUseCase;
import com.jcaa.usersmanagement.application.port.out.FindAllVehiculosPort;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllVehiculosService implements GetAllVehiculosUseCase {

    private final FindAllVehiculosPort findAllVehiculosPort;

    @Override
    public List<VehiculoModel> execute() {
        log.info("Retrieving all vehicles");

        return findAllVehiculosPort.findAll();
    }
}