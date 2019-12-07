package robot.domain;

import robot.infra.State;

import static robot.infra.State.ensure;

public class Robot {
  private final Field field;
  private final RobotPosition position;

  public Robot(Field field, RobotPosition position) {
    this.field = field;
    this.position = position;
  }

  public void positionTo(Report report) {
    report.accept(position);
  }

  public void complete(Action action) {
    switch (action) {
      case MOVE_FORWARD:
        moveForward();
        break;
      case TURN_LEFT:
        position.turnLeft();
        break;
      case TURN_RIGHT:
        position.turnRight();
        break;
    }
  }

  private void moveForward() {
    position.moveForward();
    ensure(position.isInside(field), RobotMovedOutOfField::new);
  }
}
