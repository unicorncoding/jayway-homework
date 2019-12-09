package robot.domain.errors;

import robot.domain.UserInputError;

public class InvalidRoomDimensions extends UserInputError {
  public InvalidRoomDimensions(String dimensionString) {
    super(String.format("Room dimension [%s] contains invalid chars.", dimensionString));
  }
}
