package robot.domain.errors;

import robot.domain.UserInputError;

public class InvalidRobotPositionDefinition extends UserInputError {
  public InvalidRobotPositionDefinition(String positionDefinition) {
    super(String.format("Robot position [%s] contains invalid chars.", positionDefinition));
  }
}
