package robot.domain;

public interface RobotPosition {
  void forward();

  void left();

  void right();

  int x();

  int y();

  Direction direction();

  enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
  }
}
