
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.out.FindVehiculoByPlacaPort;
import com.jcaa.usersmanagement.application.port.out.SaveVehiculoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateVehiculoCommand;
import com.jcaa.usersmanagement.domain.exception.VehiculoAlreadyExistsException;
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
class CreateVehiculoServiceTest {

    @Mock
    private SaveVehiculoPort saveVehiculoPort;

    @Mock
    private FindVehiculoByPlacaPort findVehiculoByPlacaPort;

    @Mock
    private Validator validator;

    @InjectMocks
    private CreateVehiculoService service;

    private CreateVehiculoCommand command;
    private VehiculoModel vehiculo;

    @BeforeEach
    void setUp() {
        command = new CreateVehiculoCommand(
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

        vehiculo = mock(VehiculoModel.class);
    }

    @Test
    void shouldCreateVehiculoSuccessfully() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenReturn(Optional.empty());

        when(saveVehiculoPort.save(any(VehiculoModel.class)))
                .thenReturn(vehiculo);

        VehiculoModel result = service.execute(command);

        assertNotNull(result);
        assertSame(vehiculo, result);

        verify(validator).validate(command);
        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verify(saveVehiculoPort).save(any(VehiculoModel.class));
    }

    @Test
    void shouldThrowExceptionWhenVehiculoAlreadyExists() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenReturn(Optional.of(vehiculo));

        assertThrows(
                VehiculoAlreadyExistsException.class,
                () -> service.execute(command)
        );

        verify(validator).validate(command);
        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verify(saveVehiculoPort, never()).save(any(VehiculoModel.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowConstraintViolationExceptionWhenCommandIsInvalid() {
        ConstraintViolation<CreateVehiculoCommand> violation =
                mock(ConstraintViolation.class);

        when(validator.validate(command)).thenReturn(Set.of(violation));

        assertThrows(
                ConstraintViolationException.class,
                () -> service.execute(command)
        );

        verify(validator).validate(command);
        verifyNoInteractions(findVehiculoByPlacaPort, saveVehiculoPort);
    }

    @Test
    void shouldNotSaveVehiculoWhenPlateDoesNotExistCheckFails() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(
                RuntimeException.class,
                () -> service.execute(command)
        );

        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verifyNoInteractions(saveVehiculoPort);
    }
}
