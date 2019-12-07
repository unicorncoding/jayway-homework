package robot.domain;

import org.junit.jupiter.api.Test;
import robot.infra.TestSubject;

import static org.mockito.Mockito.*;

public class RobotShould {

  RobotPosition position = mock(RobotPosition.class);

  @TestSubject Robot robot = new Robot(position);

  @Test
  public void reportPosition() {
    Report report = mock(Report.class);
    robot.positionTo(report);

    verify(report, only()).accept(position);
  }

  @Test
  public void moveForward() {
    robot.complete(Action.MOVE_FORWARD);

    verify(position, only()).forward();
  }

  @Test
  public void turnLeft() {
    robot.complete(Action.TURN_LEFT);

    verify(position, only()).left();
  }

  @Test
  public void turnRight() {
    robot.complete(Action.TURN_RIGHT);

    verify(position, only()).right();
  }
}
