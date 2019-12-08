package robot.domain;

public class Robot {
  private final RobotPosition position;

  public Robot(RobotPosition position) {
    this.position = position;
  }

  public void positionTo(Report report) {
    report.accept(position);
  }

  public void complete(RobotCommand action) {
    switch (action) {
      case MOVE_FORWARD:
        position.forward();
        break;
      case TURN_LEFT:
        position.left();
        break;
      case TURN_RIGHT:
        position.right();
        break;
    }
  }
}
