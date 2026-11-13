
package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllVehiculosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetVehiculoByPlacaUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateVehiculoUseCase;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateVehiculoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateVehiculoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.VehiculoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.VehiculoDesktopMapper;

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
class VehiculoControllerTest {

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
    private VehiculoController controller;

    private VehiculoModel createVehiculo() {
        return VehiculoModel.create(
                new VehiculoPlaca("ABC123"),
                new com.jcaa.usersmanagement.domain.valueobject.VehiculoMarca("Toyota"),
                new com.jcaa.usersmanagement.domain.valueobject.VehiculoModelo("Corolla"),
                new com.jcaa.usersmanagement.domain.valueobject.VehiculoVersion("XEI"),
                new com.jcaa.usersmanagement.domain.valueobject.VehiculoColor("Blanco"),
                new com.jcaa.usersmanagement.domain.valueobject.VehiculoNumPuestos(5),
                new com.jcaa.usersmanagement.domain.valueobject.VehiculoNumPuertas(4),
                com.jcaa.usersmanagement.domain.enums.VehiculoCombustible.GASOLINA,
                new com.jcaa.usersmanagement.domain.valueobject.VehiculoKilometros(50000),
                new com.jcaa.usersmanagement.domain.valueobject.VehiculoCilindraje(1800),
                com.jcaa.usersmanagement.domain.enums.VehiculoCategoria.AUTOMOVIL
        );
    }

    @Test
    void shouldListAllVehiculos() {
        List<VehiculoModel> vehiculos = List.of(createVehiculo());

        when(getAllVehiculosUseCase.execute()).thenReturn(vehiculos);

        List<VehiculoResponse> result = controller.listAllVehiculos();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(getAllVehiculosUseCase).execute();
    }

    @Test
    void shouldReturnEmptyListWhenNoVehiculosExist() {
        when(getAllVehiculosUseCase.execute()).thenReturn(List.of());

        List<VehiculoResponse> result = controller.listAllVehiculos();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(getAllVehiculosUseCase).execute();
    }

    @Test
    void shouldFindVehiculoByPlaca() {
        VehiculoModel vehiculo = createVehiculo();

        when(getVehiculoByPlacaUseCase.execute(any()))
                .thenReturn(vehiculo);

        VehiculoResponse result =
                controller.findVehiculoByPlaca("ABC123");

        assertNotNull(result);

        verify(getVehiculoByPlacaUseCase).execute(any());
    }

    @Test
    void shouldCreateVehiculo() {
        CreateVehiculoRequest request = mock(CreateVehiculoRequest.class);
        VehiculoModel vehiculo = createVehiculo();

        when(createVehiculoUseCase.execute(any()))
                .thenReturn(vehiculo);

        VehiculoResponse result =
                controller.createVehiculo(request);

        assertNotNull(result);

        verify(createVehiculoUseCase).execute(any());
    }

    @Test
    void shouldUpdateVehiculo() {
        UpdateVehiculoRequest request = mock(UpdateVehiculoRequest.class);
        VehiculoModel vehiculo = createVehiculo();

        when(updateVehiculoUseCase.execute(any()))
                .thenReturn(vehiculo);

        VehiculoResponse result =
                controller.updateVehiculo(request);

        assertNotNull(result);

        verify(updateVehiculoUseCase).execute(any());
    }

    @Test
    void shouldDeleteVehiculo() {
        String placa = "ABC123";

        doNothing().when(deleteVehiculoUseCase).execute(any());

        assertDoesNotThrow(() -> controller.deleteVehiculo(placa));

        verify(deleteVehiculoUseCase).execute(any());
    }
}
