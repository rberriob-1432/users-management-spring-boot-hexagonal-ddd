package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllVehiculosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetVehiculoByPlacaUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateVehiculoUseCase;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateVehiculoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateVehiculoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.VehiculoRestResponse;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.VehiculoEntity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.VehiculoPersistenceMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehiculoRestControllerTest {

    @Mock
    private CreateVehiculoUseCase createVehiculoUseCase;

    @Mock
    private UpdateVehiculoUseCase updateVehiculoUseCase;

    @Mock
    private DeleteVehiculoUseCase deleteVehiculoUseCase;

    @Mock
    private GetVehiculoByPlacaUseCase getVehiculoByPlacaUseCase;

    @Mock
    private GetAllVehiculosUseCase getAllVehiculosUseCase;

    @InjectMocks
    private VehiculoRestController controller;

    private VehiculoModel createVehiculo() {
        VehiculoEntity entity = new VehiculoEntity(
                "ABC123",
                "Toyota",
                "Corolla",
                "XEI",
                "Blanco",
                5,
                4,
                "GASOLINA",
                50000,
                1800,
                "AUTOMOVIL"
        );

        return VehiculoPersistenceMapper.fromEntityToModel(entity);
    }

    @Test
    void shouldCreateVehiculo() {
        CreateVehiculoRestRequest request =
                mock(CreateVehiculoRestRequest.class);

        VehiculoModel vehiculo = createVehiculo();

        when(createVehiculoUseCase.execute(any()))
                .thenReturn(vehiculo);

        VehiculoRestResponse result = controller.create(request);

        assertNotNull(result);

        verify(createVehiculoUseCase).execute(any());
    }

    @Test
    void shouldGetAllVehiculos() {
        List<VehiculoModel> vehiculos = List.of(createVehiculo());

        when(getAllVehiculosUseCase.execute())
                .thenReturn(vehiculos);

        List<VehiculoRestResponse> result = controller.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(getAllVehiculosUseCase).execute();
    }

    @Test
    void shouldReturnEmptyListWhenNoVehiculosExist() {
        when(getAllVehiculosUseCase.execute())
                .thenReturn(List.of());

        List<VehiculoRestResponse> result = controller.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(getAllVehiculosUseCase).execute();
    }

    @Test
    void shouldGetVehiculoByPlaca() {
        VehiculoModel vehiculo = createVehiculo();

        when(getVehiculoByPlacaUseCase.execute(any()))
                .thenReturn(vehiculo);

        VehiculoRestResponse result =
                controller.getByPlaca("ABC123");

        assertNotNull(result);

        verify(getVehiculoByPlacaUseCase).execute(any());
    }

    @Test
    void shouldUpdateVehiculo() {
        UpdateVehiculoRestRequest request =
                mock(UpdateVehiculoRestRequest.class);

        VehiculoModel vehiculo = createVehiculo();

        when(updateVehiculoUseCase.execute(any()))
                .thenReturn(vehiculo);

        VehiculoRestResponse result =
                controller.update("ABC123", request);

        assertNotNull(result);

        verify(updateVehiculoUseCase).execute(any());
    }

    @Test
    void shouldDeleteVehiculo() {
        String placa = "ABC123";

        assertDoesNotThrow(() -> controller.delete(placa));

        verify(deleteVehiculoUseCase).execute(any());
    }
}
