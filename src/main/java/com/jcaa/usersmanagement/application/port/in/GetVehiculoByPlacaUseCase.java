package com.jcaa.usersmanagement.application.port.in;
import com.jcaa.usersmanagement.application.service.dto.query.GetVehiculoByPlacaQuery;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetVehiculoByPlacaUseCase {

    VehiculoModel execute(@NotNull @Valid GetVehiculoByPlacaQuery query);
}