package robot.domain;

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

    public static Direction fromDisplayString(String inputSymbol) {
      return null;
    }
  }
}
