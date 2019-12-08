package robot.domain.userinput;

public interface UserInput {
  ValidRoomDimensions roomDimensions();

  ValidRobotPositionDefinition positionDefinitions();

  ValidRobotCommandSequence commandSequence();
}
