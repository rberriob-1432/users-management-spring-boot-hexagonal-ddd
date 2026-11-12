package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.out.DeleteVehiculoPort;
import com.jcaa.usersmanagement.application.port.out.FindVehiculoByPlacaPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteVehiculoCommand;
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
class DeleteVehiculoServiceTest {

    @Mock
    private DeleteVehiculoPort deleteVehiculoPort;

    @Mock
    private FindVehiculoByPlacaPort findVehiculoByPlacaPort;

    @Mock
    private Validator validator;

    @InjectMocks
    private DeleteVehiculoService service;

    private DeleteVehiculoCommand command;
    private VehiculoModel vehiculo;

    @BeforeEach
    void setUp() {
        command = new DeleteVehiculoCommand("ABC123");
        vehiculo = mock(VehiculoModel.class);
    }

    @Test
    void shouldDeleteVehiculoSuccessfully() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenReturn(Optional.of(vehiculo));

        doNothing().when(deleteVehiculoPort)
                .deleteByPlaca(any(VehiculoPlaca.class));

        assertDoesNotThrow(() -> service.execute(command));

        verify(validator).validate(command);
        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verify(deleteVehiculoPort).deleteByPlaca(any(VehiculoPlaca.class));
    }

    @Test
    void shouldThrowExceptionWhenVehiculoDoesNotExist() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                VehiculoNotFoundException.class,
                () -> service.execute(command)
        );

        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verifyNoInteractions(deleteVehiculoPort);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowConstraintViolationExceptionWhenCommandIsInvalid() {
        ConstraintViolation<DeleteVehiculoCommand> violation =
                mock(ConstraintViolation.class);

        when(validator.validate(command)).thenReturn(Set.of(violation));

        assertThrows(
                ConstraintViolationException.class,
                () -> service.execute(command)
        );

        verify(validator).validate(command);
        verifyNoInteractions(findVehiculoByPlacaPort, deleteVehiculoPort);
    }

    @Test
    void shouldNotDeleteVehiculoWhenFindOperationFails() {
        when(validator.validate(command)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(
                RuntimeException.class,
                () -> service.execute(command)
        );

        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
        verifyNoInteractions(deleteVehiculoPort);
    }
}
