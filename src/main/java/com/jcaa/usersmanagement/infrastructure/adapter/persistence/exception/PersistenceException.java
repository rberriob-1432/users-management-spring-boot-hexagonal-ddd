package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class PersistenceException extends RuntimeException {

  private static final String VEHICLE_MESSAGE_SAVE =
          "Failed to save vehicle with plate: '%s'.";

  private static final String VEHICLE_MESSAGE_UPDATE =
          "Failed to update vehicle with plate: '%s'.";

  private static final String VEHICLE_MESSAGE_FIND =
          "Failed to find vehicle with plate: '%s'.";

  private static final String VEHICLE_MESSAGE_ALL =
          "Failed to retrieve all vehicles.";

  private static final String VEHICLE_MESSAGE_DELETE =
          "Failed to delete vehicle with plate: '%s'.";

  // USERS
  private static final String USER_MESSAGE_SAVE =
          "Failed to save user with ID: '%s'.";

  private static final String USER_MESSAGE_UPDATE =
          "Failed to update user with ID: '%s'.";

  private static final String USER_MESSAGE_FIND =
          "Failed to find user with ID: '%s'.";

  private static final String USER_MESSAGE_EMAIL =
          "Failed to find user with email: '%s'.";

  private static final String USER_MESSAGE_ALL =
          "Failed to retrieve all users.";

  private static final String USER_MESSAGE_DELETE =
          "Failed to delete user with ID: '%s'.";

  // COMMON
  private static final String MESSAGE_CONNECTION =
          "Could not establish database connection.";

  private PersistenceException(
          final String message,
          final Throwable cause
  ) {
    super(message, cause);
  }

  // VEHICLE FACTORY METHODS

  public static PersistenceException becauseVehicleSaveFailed(
          final String placa,
          final Throwable cause
  ) {
    return new PersistenceException(
            String.format(VEHICLE_MESSAGE_SAVE, placa), cause);
  }

  public static PersistenceException becauseVehicleUpdateFailed(
          final String placa,
          final Throwable cause
  ) {
    return new PersistenceException(
            String.format(VEHICLE_MESSAGE_UPDATE, placa), cause);
  }

  public static PersistenceException becauseVehicleFindByPlacaFailed(
          final String placa,
          final Throwable cause
  ) {
    return new PersistenceException(
            String.format(VEHICLE_MESSAGE_FIND, placa), cause);
  }

  public static PersistenceException becauseVehicleFindAllFailed(
          final Throwable cause
  ) {
    return new PersistenceException(VEHICLE_MESSAGE_ALL, cause);
  }

  public static PersistenceException becauseVehicleDeleteFailed(
          final String placa,
          final Throwable cause
  ) {
    return new PersistenceException(
            String.format(VEHICLE_MESSAGE_DELETE, placa), cause);
  }

  // USER FACTORY METHODS

  public static PersistenceException becauseUserSaveFailed(
          final String userId,
          final Throwable cause
  ) {
    return new PersistenceException(
            String.format(USER_MESSAGE_SAVE, userId), cause);
  }

  public static PersistenceException becauseUserUpdateFailed(
          final String userId,
          final Throwable cause
  ) {
    return new PersistenceException(
            String.format(USER_MESSAGE_UPDATE, userId), cause);
  }

  public static PersistenceException becauseUserFindByIdFailed(
          final String userId,
          final Throwable cause
  ) {
    return new PersistenceException(
            String.format(USER_MESSAGE_FIND, userId), cause);
  }

  public static PersistenceException becauseUserFindByEmailFailed(
          final String email,
          final Throwable cause
  ) {
    return new PersistenceException(
            String.format(USER_MESSAGE_EMAIL, email), cause);
  }

  public static PersistenceException becauseUserFindAllFailed(
          final Throwable cause
  ) {
    return new PersistenceException(USER_MESSAGE_ALL, cause);
  }

  public static PersistenceException becauseUserDeleteFailed(
          final String userId,
          final Throwable cause
  ) {
    return new PersistenceException(
            String.format(USER_MESSAGE_DELETE, userId), cause);
  }

  public static PersistenceException becauseConnectionFailed(
          final Throwable cause
  ) {
    return new PersistenceException(MESSAGE_CONNECTION, cause);
  }
}
