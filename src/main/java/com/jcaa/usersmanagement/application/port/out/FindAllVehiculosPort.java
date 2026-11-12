package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.VehiculoModel;

import java.util.List;

public interface FindAllVehiculosPort {

    List<VehiculoModel> findAll();
}