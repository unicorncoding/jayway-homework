package robot.domain;

import robot.domain.userinput.ValidRobotCommandSequence;
import robot.domain.userinput.ValidRobotPositionDefinition;
import robot.domain.userinput.ValidRoomDimensions;

public class UserCommandFactory {
  public static UserCommandFactory INSTANCE = new UserCommandFactory();

  private UserCommandFactory() {};

  public ValidRoomDimensions roomDimensions(String input) {
    return new ValidRoomDimensions(input);
  }

  public ValidRobotPositionDefinition positionDefinition(String input) {
    return new ValidRobotPositionDefinition(input);
  }

  public ValidRobotCommandSequence commandSequence(String input) {
    return new ValidRobotCommandSequence(input);
  }
}
