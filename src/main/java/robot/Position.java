package robot;

import static java.lang.String.format;
import static robot.Direction.*;

class Position {

  private final Room room;
  private Direction direction;
  private int x;
  private int y;

  public Position(Room room, Direction direction, int x, int y) {
    this.room = room;
    this.direction = direction;
    this.x = x;
    this.y = y;
    new EnsureIsWithinRoomBounds();
  }

  void forward() {
    switch (direction) {
      case NORTH:
        y--;
        break;
      case SOUTH:
        y++;
        break;
      case EAST:
        x++;
        break;
      case WEST:
        x--;
        break;
    }
    new EnsureIsWithinRoomBounds();
  }

  void left() {
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

  void right() {
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
  public String toString() {
    return format("Report: %s %s %s", x, y, direction);
  }

  class EnsureIsWithinRoomBounds {
    EnsureIsWithinRoomBounds() {
      boolean outOfRoom = x > room.width() || y > room.height() || x <= 0 || y <= 0;
      if (outOfRoom) {
        throw new IllegalArgumentException(format(
                "Robot can't be placed/move outside room. Room dimensions are [%s, %s], but Robot placed at [%s %s]",
                room.width(), room.height(), x, y));
      }
    }
  }
}
