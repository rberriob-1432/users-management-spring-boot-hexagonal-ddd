package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;

import java.util.List;

public interface GetAllVehiculosUseCase {

    List<VehiculoModel> execute();
}
