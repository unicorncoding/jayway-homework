package robot;

enum Direction {

  NORTH("N"),
  EAST("E"),
  SOUTH("S"),
  WEST("W");

  public final String displayName;

  Direction(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
