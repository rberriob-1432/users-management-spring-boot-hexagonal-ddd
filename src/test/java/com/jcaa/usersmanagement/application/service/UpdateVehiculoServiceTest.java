package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.out.FindVehiculoByPlacaPort;
import com.jcaa.usersmanagement.application.port.out.UpdateVehiculoPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateVehiculoCommand;
import com.jcaa.usersmanagement.domain.exception.VehiculoNotFoundException;
import com.jcaa.usersmanagement.domain.model.VehiculoModel;
import com.jcaa.usersmanagement.domain.valueobject.VehiculoPlaca;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateVehiculoServiceTest {

    @Mock
    private UpdateVehiculoPort updateVehiculoPort;

    @Mock
    private FindVehiculoByPlacaPort findVehiculoByPlacaPort;

    @Mock
    private Validator validator;

    @InjectMocks
    private UpdateVehiculoService service;

    private UpdateVehiculoCommand command;
    private VehiculoModel vehiculo;

    @BeforeEach
    void setUp() {
        command = new UpdateVehiculoCommand(
                "ABC123",
                "Mazda",
                "CX-5",
                "Grand Touring",
                "Negro",
                5,
                4,
                "DIESEL",
                30000,
                2200,
                "CAMIONETA"
        );

        vehiculo = mock(VehiculoModel.class);
    }

    @Test
    void shouldUpdateVehiculoSuccessfully() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenReturn(Optional.of(vehiculo));

        when(updateVehiculoPort.update(any(VehiculoModel.class)))
                .thenReturn(vehiculo);

        VehiculoModel result = service.execute(command);

        assertNotNull(result);
        assertSame(vehiculo, result);

        verify(validator).validate(command);
        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verify(updateVehiculoPort).update(any(VehiculoModel.class));
    }

    @Test
    void shouldThrowVehiculoNotFoundExceptionWhenVehiculoDoesNotExist() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                VehiculoNotFoundException.class,
                () -> service.execute(command)
        );

        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verifyNoInteractions(updateVehiculoPort);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowConstraintViolationExceptionWhenCommandIsInvalid() {
        ConstraintViolation<UpdateVehiculoCommand> violation =
                mock(ConstraintViolation.class);

        when(validator.validate(command)).thenReturn(Set.of(violation));

        assertThrows(
                ConstraintViolationException.class,
                () -> service.execute(command)
        );

        verify(validator).validate(command);
        verifyNoInteractions(findVehiculoByPlacaPort, updateVehiculoPort);
    }

    @Test
    void shouldPropagateExceptionWhenFindByPlacaFails() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(
                RuntimeException.class,
                () -> service.execute(command)
        );

        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verifyNoInteractions(updateVehiculoPort);
    }

    @Test
    void shouldPropagateExceptionWhenUpdateFails() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenReturn(Optional.of(vehiculo));

        when(updateVehiculoPort.update(any(VehiculoModel.class)))
                .thenThrow(new RuntimeException("Update failed"));

        assertThrows(
                RuntimeException.class,
                () -> service.execute(command)
        );

        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verify(updateVehiculoPort).update(any(VehiculoModel.class));
    }
}
