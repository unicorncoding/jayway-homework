package robot.domain.errors;

import robot.domain.UserInputError;

public class InvalidRobotDirection extends UserInputError {
  public InvalidRobotDirection(String unknownSymbol) {
    super(String.format("Unknown direction symbol [%s]", unknownSymbol));
  }
}
