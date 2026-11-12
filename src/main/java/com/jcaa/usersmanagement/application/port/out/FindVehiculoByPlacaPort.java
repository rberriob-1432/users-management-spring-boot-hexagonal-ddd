package com.jcaa.usersmanagement.application.port.out;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;
import java.util.Optional;

public interface FindVehiculoByPlacaPort {

    Optional<VehiculoModel> findByPlaca(VehiculoPlaca placa);
}