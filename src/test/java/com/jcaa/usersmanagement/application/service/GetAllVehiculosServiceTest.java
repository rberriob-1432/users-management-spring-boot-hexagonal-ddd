package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.out.FindAllVehiculosPort;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllVehiculosServiceTest {

    @Mock
    private FindAllVehiculosPort findAllVehiculosPort;

    @InjectMocks
    private GetAllVehiculosService service;

    @Test
    void shouldReturnAllVehiculos() {
        List<VehiculoModel> vehiculos = List.of(
                mock(VehiculoModel.class),
                mock(VehiculoModel.class)
        );

        when(findAllVehiculosPort.findAll()).thenReturn(vehiculos);

        List<VehiculoModel> result = service.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertSame(vehiculos, result);

        verify(findAllVehiculosPort).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoVehiculosExist() {
        when(findAllVehiculosPort.findAll()).thenReturn(List.of());

        List<VehiculoModel> result = service.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(findAllVehiculosPort).findAll();
    }

    @Test
    void shouldPropagateExceptionWhenFindAllFails() {
        when(findAllVehiculosPort.findAll())
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(
                RuntimeException.class,
                () -> service.execute()
        );

        verify(findAllVehiculosPort).findAll();
    }
}
