package robot.domain;

public class Robot {
  private final RobotPosition position;

  public Robot(RobotPosition position) {
    this.position = position;
  }

  public void positionTo(Report report) {
    report.accept(position);
  }

  public void complete(Action action) {
    switch (action) {
      case MOVE_FORWARD:
        position.moveForward();
        break;
      case TURN_LEFT:
        position.turnLeft();
        break;
      case TURN_RIGHT:
        position.turnRight();
        break;
    }
  }
}
