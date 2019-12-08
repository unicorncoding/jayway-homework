package robot.domain;

import robot.domain.errors.InvalidRobotDirection;

import java.util.Arrays;

public interface RobotPosition {
  void forward();

  void left();

  void right();

  int x();

  int y();

  Direction direction();

  enum Direction {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    public final String displayName;

    Direction(String displayName) {
      this.displayName = displayName;
    }

    public static Direction fromDisplayName(String displayName) {
      return Arrays.stream(values())
          .filter(direction -> direction.displayName.equals(displayName))
          .findAny()
          .orElseThrow(InvalidRobotDirection::new);
    }
  }
}
