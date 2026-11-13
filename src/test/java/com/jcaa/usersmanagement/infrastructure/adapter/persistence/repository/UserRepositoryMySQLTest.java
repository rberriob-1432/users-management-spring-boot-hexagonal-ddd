
package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.UserPersistenceMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryMySQLTest {

  @Mock
  private DataSource dataSource;

  @Mock
  private Connection connection;

  @Mock
  private PreparedStatement statement;

  @Mock
  private ResultSet resultSet;

  @Mock
  private UserModel user;

  @InjectMocks
  private UserRepositoryMySQL repository;

  @BeforeEach
  void setUp() throws SQLException {
    when(dataSource.getConnection()).thenReturn(connection);
  }

  @Test
  void shouldReturnUserWhenFoundById() throws SQLException {
    UserId userId = mock(UserId.class);

    when(userId.value()).thenReturn("USR-001");
    when(connection.prepareStatement(anyString())).thenReturn(statement);
    when(resultSet.next()).thenReturn(true);
    when(statement.executeQuery()).thenReturn(resultSet);

    try (MockedStatic<UserPersistenceMapper> mapper =
                 mockStatic(UserPersistenceMapper.class)) {

      mapper.when(() ->
              UserPersistenceMapper.fromResultSetToModel(resultSet)
      ).thenReturn(user);

      Optional<UserModel> result = repository.getById(userId);

      assertTrue(result.isPresent());
      assertSame(user, result.get());

      verify(statement).setString(1, "USR-001");
      verify(statement).executeQuery();
    }
  }

  @Test
  void shouldReturnEmptyWhenUserIdDoesNotExist() throws SQLException {
    UserId userId = mock(UserId.class);

    when(userId.value()).thenReturn("USR-999");
    when(connection.prepareStatement(anyString())).thenReturn(statement);
    when(resultSet.next()).thenReturn(false);
    when(statement.executeQuery()).thenReturn(resultSet);

    Optional<UserModel> result = repository.getById(userId);

    assertTrue(result.isEmpty());

    verify(statement).setString(1, "USR-999");
    verify(statement).executeQuery();
  }

  @Test
  void shouldReturnUserWhenFoundByEmail() throws SQLException {
    UserEmail email = mock(UserEmail.class);

    when(email.value()).thenReturn("test@example.com");
    when(connection.prepareStatement(anyString())).thenReturn(statement);
    when(resultSet.next()).thenReturn(true);
    when(statement.executeQuery()).thenReturn(resultSet);

    try (MockedStatic<UserPersistenceMapper> mapper =
                 mockStatic(UserPersistenceMapper.class)) {

      mapper.when(() ->
              UserPersistenceMapper.fromResultSetToModel(resultSet)
      ).thenReturn(user);

      Optional<UserModel> result = repository.getByEmail(email);

      assertTrue(result.isPresent());
      assertSame(user, result.get());

      verify(statement).setString(1, "test@example.com");
      verify(statement).executeQuery();
    }
  }

  @Test
  void shouldReturnAllUsers() throws SQLException {
    List<UserModel> users = List.of(user, user);

    when(connection.prepareStatement(anyString())).thenReturn(statement);
    when(statement.executeQuery()).thenReturn(resultSet);

    try (MockedStatic<UserPersistenceMapper> mapper =
                 mockStatic(UserPersistenceMapper.class)) {

      mapper.when(() ->
              UserPersistenceMapper.fromResultSetToModelList(resultSet)
      ).thenReturn(users);

      List<UserModel> result = repository.getAll();

      assertEquals(2, result.size());
      assertSame(users, result);
    }
  }

  @Test
  void shouldDeleteUser() throws SQLException {
    UserId userId = mock(UserId.class);

    when(userId.value()).thenReturn("USR-001");
    when(connection.prepareStatement(anyString())).thenReturn(statement);

    repository.delete(userId);

    verify(statement).setString(1, "USR-001");
    verify(statement).executeUpdate();
  }

  @Test
  void shouldThrowPersistenceExceptionWhenFindingByIdFails()
          throws SQLException {

    UserId userId = mock(UserId.class);

    when(userId.value()).thenReturn("USR-001");
    when(dataSource.getConnection())
            .thenThrow(new SQLException("Database connection error"));

    assertThrows(
            PersistenceException.class,
            () -> repository.getById(userId)
    );
  }

  @Test
  void shouldThrowPersistenceExceptionWhenDeletingFails()
          throws SQLException {

    UserId userId = mock(UserId.class);

    when(userId.value()).thenReturn("USR-001");
    when(connection.prepareStatement(anyString())).thenReturn(statement);
    when(statement.executeUpdate())
            .thenThrow(new SQLException("Delete failed"));

    assertThrows(
            PersistenceException.class,
            () -> repository.delete(userId)
    );
  }
}
