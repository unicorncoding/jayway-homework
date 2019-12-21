package robot;

class Room {

  private final int width;
  private final int height;

  public Room(int width, int height) {
    this.width = width;
    this.height = height;
  }

  int width() {
    return width;
  }

  int height() {
    return height;
  }

}
