package robot.domain.userinput;

class UserCommandFactory {
  public static UserCommandFactory INSTANCE = new UserCommandFactory();

  private UserCommandFactory() {};

  ValidRoomDimensions roomDimensions(String input) {
    return new ValidRoomDimensions(input);
  }

  ValidRobotPositionDefinition positionDefinition(String input) {
    return new ValidRobotPositionDefinition(input);
  }

  ValidRobotCommandSequence commandSequence(String input) {
    return new ValidRobotCommandSequence(input);
  }
}
