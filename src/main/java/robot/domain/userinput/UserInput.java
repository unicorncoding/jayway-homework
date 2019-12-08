package robot.domain.userinput;

public interface UserInput {
  ValidRoomDimensions roomDimensions();

  ValidRobotPositionDefinition positionDefinition();

  ValidRobotCommandSequence commandSequence();
}
