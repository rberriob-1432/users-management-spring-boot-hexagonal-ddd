package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;

public interface SaveVehiculoPort {

    VehiculoModel save(VehiculoModel vehiculo);
}