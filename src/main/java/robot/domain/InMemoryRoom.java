package robot.domain;

import robot.domain.userinput.ValidRoomDimensions;

public class InMemoryRoom implements Room {
  private final int width;
  private final int height;

  public InMemoryRoom(ValidRoomDimensions roomDimensions) {
    this.width = roomDimensions.width;
    this.height = roomDimensions.height;
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
