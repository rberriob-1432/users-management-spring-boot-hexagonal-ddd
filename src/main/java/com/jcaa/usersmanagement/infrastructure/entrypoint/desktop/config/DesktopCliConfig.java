package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.config;

import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;

import com.jcaa.usersmanagement.application.port.in.CreateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteVehiculoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetVehiculoByPlacaUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllVehiculosUseCase;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.VehiculoManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.VehiculoController;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        name = "app.desktop-cli.enabled",
        havingValue = "true"
)
public class DesktopCliConfig {

    @Bean
    ConsoleIO consoleIO() {
        return new ConsoleIO(new Scanner(System.in), System.out);
    }

    @Bean
    UserController userController(
            CreateUserUseCase createUser,
            UpdateUserUseCase updateUser,
            DeleteUserUseCase deleteUser,
            GetUserByIdUseCase getUserById,
            GetAllUsersUseCase getAllUsers,
            LoginUseCase login) {

        return new UserController(
                createUser,
                updateUser,
                deleteUser,
                getUserById,
                getAllUsers,
                login
        );
    }

    @Bean
    VehiculoController vehiculoController(
            CreateVehiculoUseCase createVehiculo,
            UpdateVehiculoUseCase updateVehiculo,
            DeleteVehiculoUseCase deleteVehiculo,
            GetVehiculoByPlacaUseCase getVehiculoByPlaca,
            GetAllVehiculosUseCase getAllVehiculos) {

        return new VehiculoController(
                createVehiculo,
                updateVehiculo,
                deleteVehiculo,
                getVehiculoByPlaca,
                getAllVehiculos
        );
    }

    @Bean
    UserManagementCli userManagementCli(
            UserController controller,
            ConsoleIO console) {

        return new UserManagementCli(controller, console);
    }

    @Bean
    VehiculoManagementCli vehiculoManagementCli(
            VehiculoController controller,
            ConsoleIO console) {

        return new VehiculoManagementCli(controller, console);
    }

    @Bean
    CommandLineRunner desktopCliRunner(
            UserManagementCli userCli,
            VehiculoManagementCli vehiculoCli) {

        return args -> {
            userCli.start();
            vehiculoCli.start();
        };
    }
}
