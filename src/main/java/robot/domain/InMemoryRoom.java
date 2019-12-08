package robot.domain;

import static robot.infra.State.ensure;

public class InMemoryRoom implements Room {
  private final int width;
  private final int height;

  public InMemoryRoom(int width, int height) {
    ensure(isPositive(width), NegativeRoomDimensions::new);
    ensure(isPositive(height), NegativeRoomDimensions::new);
    this.width = width;
    this.height = height;
  }

  private boolean isPositive(int dimension) {
    return dimension > 0;
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }
}
