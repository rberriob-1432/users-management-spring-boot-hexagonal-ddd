package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.out.FindVehiculoByPlacaPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetVehiculoByPlacaQuery;
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
class GetVehiculoByPlacaServiceTest {

    @Mock
    private FindVehiculoByPlacaPort findVehiculoByPlacaPort;

    @Mock
    private Validator validator;

    @InjectMocks
    private GetVehiculoByPlacaService service;

    private GetVehiculoByPlacaQuery query;
    private VehiculoModel vehiculo;

    @BeforeEach
    void setUp() {
        query = new GetVehiculoByPlacaQuery("ABC123");
        vehiculo = mock(VehiculoModel.class);
    }

    @Test
    void shouldReturnVehiculoWhenFoundByPlaca() {
        when(validator.validate(query)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenReturn(Optional.of(vehiculo));

        VehiculoModel result = service.execute(query);

        assertNotNull(result);
        assertSame(vehiculo, result);

        verify(validator).validate(query);
        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
    }

    @Test
    void shouldThrowVehiculoNotFoundExceptionWhenPlacaDoesNotExist() {
        when(validator.validate(query)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                VehiculoNotFoundException.class,
                () -> service.execute(query)
        );

        verify(validator).validate(query);
        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowConstraintViolationExceptionWhenQueryIsInvalid() {
        ConstraintViolation<GetVehiculoByPlacaQuery> violation =
                mock(ConstraintViolation.class);

        when(validator.validate(query)).thenReturn(Set.of(violation));

        assertThrows(
                ConstraintViolationException.class,
                () -> service.execute(query)
        );

        verify(validator).validate(query);
        verifyNoInteractions(findVehiculoByPlacaPort);
    }

    @Test
    void shouldPropagateExceptionWhenFindByPlacaFails() {
        when(validator.validate(query)).thenReturn(Set.of());

        when(findVehiculoByPlacaPort.findByPlaca(any(VehiculoPlaca.class)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(
                RuntimeException.class,
                () -> service.execute(query)
        );

        verify(findVehiculoByPlacaPort).findByPlaca(any(VehiculoPlaca.class));
    }
}
