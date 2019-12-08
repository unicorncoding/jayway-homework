package robot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robot.infra.TestSubject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static robot.domain.RobotPosition.Direction.*;

class InMemoryPositionShould {
  private Field field = mock(Field.class);

  private int fieldSize = 10;
  private int x = inTheFieldMiddle();
  private int y = inTheFieldMiddle();

  private int inTheFieldMiddle() {
    return 10 / 2;
  }

  @TestSubject InMemoryPosition position;

  @BeforeEach
  void setUp() {
    when(field.width()).thenReturn(fieldSize);
    when(field.height()).thenReturn(fieldSize);
    position = new InMemoryPosition(field, NORTH, x, y);
  }

  @Test
  void notCreatePositionOutsideField() {
    assertThatThrownBy(() -> new InMemoryPosition(field, NORTH, fieldSize + 1, fieldSize))
        .isInstanceOf(PositionIsOutOfField.class);
  }

  @Test
  void incrementYOnFacingNorth() {
    position.forward();

    assertThat(position.y()).as("y").isEqualTo(y + 1);
  }

  @Test
  void decrementYOnFacingSouth() {
    position = new InMemoryPosition(field, SOUTH, x, y);
    position.forward();

    assertThat(position.y()).as("y").isEqualTo(y - 1);
  }

  @Test
  void incrementXOnFacingEast() {
    position = new InMemoryPosition(field, EAST, x, y);
    position.forward();

    assertThat(position.x()).as("x").isEqualTo(x + 1);
  }

  @Test
  void decrementXOnFacingWest() {
    position = new InMemoryPosition(field, WEST, x, y);
    position.forward();

    assertThat(position.x()).as("x").isEqualTo(x - 1);
  }

  @Test
  void changeDirectionClockwiseOnPositionRight() {
    assertThat(position.direction()).as("direction").isEqualTo(NORTH);

    position.right();
    assertThat(position.direction()).as("direction").isEqualTo(EAST);

    position.right();
    assertThat(position.direction()).as("direction").isEqualTo(SOUTH);

    position.right();
    assertThat(position.direction()).as("direction").isEqualTo(WEST);

    position.right();
    assertThat(position.direction()).as("direction").isEqualTo(NORTH);
  }

  @Test
  void changeDirectionCounterclockwiseOnPositionLeft() {
    assertThat(position.direction()).as("direction").isEqualTo(NORTH);

    position.left();
    assertThat(position.direction()).as("direction").isEqualTo(WEST);

    position.left();
    assertThat(position.direction()).as("direction").isEqualTo(SOUTH);

    position.left();
    assertThat(position.direction()).as("direction").isEqualTo(EAST);

    position.left();
    assertThat(position.direction()).as("direction").isEqualTo(NORTH);
  }

  @Test
  void notLeaveFieldOnTheRight() {
    RobotPosition positionAtRightBorderFacingIt = new InMemoryPosition(field, EAST, fieldSize, y);

    assertThatThrownBy(positionAtRightBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfField.class);
  }

  @Test
  void notLeaveFieldOnTheLeft() {
    RobotPosition positionAtLeftBorderFacingIt = new InMemoryPosition(field, WEST, 0, y);

    assertThatThrownBy(positionAtLeftBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfField.class);
  }

  @Test
  void notLeaveFieldOnTheTop() {
    RobotPosition positionAtTopBorderFacingIt = new InMemoryPosition(field, NORTH, x, fieldSize);

    assertThatThrownBy(positionAtTopBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfField.class);
  }

  @Test
  void notLeaveFieldOnTheBottom() {
    RobotPosition positionAtBottomBorderFacingIt = new InMemoryPosition(field, SOUTH, x, 0);

    assertThatThrownBy(positionAtBottomBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfField.class);
  }
}
