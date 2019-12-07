package robot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robot.infra.TestSubject;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class RobotShould {

  Field field = mock(Field.class);
  RobotPosition position = mock(RobotPosition.class);

  @TestSubject Robot robot = new Robot(field, position);

  @BeforeEach
  void setUp() {
    when(position.isInside(field)).thenReturn(true);
  }

  @Test
  public void reportPosition() {
    Report report = mock(Report.class);
    robot.positionTo(report);

    verify(report, only()).accept(position);
  }

  @Test
  public void moveForward() {
    robot.complete(Action.MOVE_FORWARD);

    verify(position, times(1)).moveForward();
  }

  @Test
  public void throwOnMovingOutOfField() {
    alwaysMoveOutOfField();

    assertThatThrownBy(() -> robot.complete(Action.MOVE_FORWARD))
        .isInstanceOf(RobotMovedOutOfField.class);

    verify(position, times(1)).isInside(field);
  }

  private void alwaysMoveOutOfField() {
    when(position.isInside(field)).thenReturn(false);
  }

  @Test
  public void turnLeft() {
    robot.complete(Action.TURN_LEFT);

    verify(position, only()).turnLeft();
  }

  @Test
  public void turnRight() {
    robot.complete(Action.TURN_RIGHT);

    verify(position, only()).turnRight();
  }
}
