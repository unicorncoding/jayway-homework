package robot.domain.errors;

import robot.domain.UserInputError;

public class InvalidRobotCommand extends UserInputError {
  public InvalidRobotCommand(String unknownSymbol) {
    super(String.format("Unknown robot command symbol [%s]", unknownSymbol));
  }
}
