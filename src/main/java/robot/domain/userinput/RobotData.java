package robot.domain.userinput;

public interface RobotData {
  ValidRoomDimensions roomDimensions();

  ValidRobotPositionDefinition positionDefinition();

  ValidRobotCommandSequence commandSequence();
}
