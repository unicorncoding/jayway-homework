package robot.domain;

import robot.domain.errors.PositionIsOutOfRoom;
import robot.domain.userinput.ValidRobotPositionDefinition;

import static robot.domain.RobotPosition.Direction.*;
import static robot.infra.State.ensure;

public class InMemoryPosition implements RobotPosition {
  private Room room;
  private Direction direction;
  private int x;
  private int y;

  public InMemoryPosition(Room room, ValidRobotPositionDefinition positionDefinition) {
    this.room = room;
    this.direction = positionDefinition.direction;
    this.x = positionDefinition.x;
    this.y = positionDefinition.y;
    ensure(isInsideRoom(), PositionIsOutOfRoom::new);
  }

  @Override
  public void forward() {
    switch (direction) {
      case NORTH:
        y++;
        break;
      case SOUTH:
        y--;
        break;
      case EAST:
        x++;
        break;
      case WEST:
        x--;
        break;
    }
    ensure(isInsideRoom(), PositionIsOutOfRoom::new);
  }

  private boolean isInsideRoom() {
    return x <= room.width() && y <= room.height() && x >= 0 && y >= 0;
  }

  @Override
  public void left() {
    switch (direction) {
      case NORTH:
        direction = WEST;
        break;
      case WEST:
        direction = SOUTH;
        break;
      case SOUTH:
        direction = EAST;
        break;
      case EAST:
        direction = NORTH;
        break;
    }
  }

  // Of course this method could be implemented using array position +1 and return 0 on max index.
  // But this way it much easier to understand what exactly this method doing
  @Override
  public void right() {
    switch (direction) {
      case NORTH:
        direction = EAST;
        break;
      case EAST:
        direction = SOUTH;
        break;
      case SOUTH:
        direction = WEST;
        break;
      case WEST:
        direction = NORTH;
        break;
    }
  }

  @Override
  public int x() {
    return x;
  }

  @Override
  public int y() {
    return y;
  }

  @Override
  public Direction direction() {
    return direction;
  }

  public static class IsOutOfFirstCartesianQuadrant extends IllegalStateException {}
}
