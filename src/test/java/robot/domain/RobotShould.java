package robot.domain;

import org.junit.jupiter.api.Test;
import robot.infra.TestSubject;

import static org.mockito.Mockito.*;

public class RobotShould {

  Field field = mock(Field.class);
  RobotPosition position = mock(RobotPosition.class);

  @TestSubject Robot robot = new Robot(field, position);

  @Test
  public void reportPosition() {
    Report report = mock(Report.class);
    robot.positionTo(report);

    verify(report, only()).accept(position);
  }
}
