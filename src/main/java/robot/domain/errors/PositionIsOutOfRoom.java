package robot.domain.errors;

import robot.domain.RobotPosition;
import robot.domain.Room;
import robot.domain.UserInputError;

public class PositionIsOutOfRoom extends UserInputError {
  public PositionIsOutOfRoom(RobotPosition position, Room room) {
    super(
        String.format(
            "Robot can't be placed/move outside room. Room dimensions are [%s, %s], but Robot placed at [%s %s]",
            room.width(), room.height(), position.x(), position.y()));
  }
}
