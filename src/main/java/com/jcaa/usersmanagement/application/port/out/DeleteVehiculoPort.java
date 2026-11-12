package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;

public interface DeleteVehiculoPort {

    void deleteByPlaca(VehiculoPlaca placa);
}