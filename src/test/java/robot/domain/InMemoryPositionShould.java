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
  private Room room = mock(Room.class);

  private int roomSize = 10;
  private int x = inTheMiddleOfTheRoom();
  private int y = inTheMiddleOfTheRoom();

  private int inTheMiddleOfTheRoom() {
    return 10 / 2;
  }

  @TestSubject InMemoryPosition position;

  @BeforeEach
  void setUp() {
    when(room.width()).thenReturn(roomSize);
    when(room.height()).thenReturn(roomSize);
    position = new InMemoryPosition(room, NORTH, x, y);
  }

  @Test
  void notCreatePositionOutsideField() {
    assertThatThrownBy(() -> new InMemoryPosition(room, NORTH, roomSize + 1, roomSize))
        .isInstanceOf(PositionIsOutOfField.class);
  }

  @Test
  void incrementYOnFacingNorth() {
    position.forward();

    assertThat(position.y()).as("y").isEqualTo(y + 1);
  }

  @Test
  void decrementYOnFacingSouth() {
    position = new InMemoryPosition(room, SOUTH, x, y);
    position.forward();

    assertThat(position.y()).as("y").isEqualTo(y - 1);
  }

  @Test
  void incrementXOnFacingEast() {
    position = new InMemoryPosition(room, EAST, x, y);
    position.forward();

    assertThat(position.x()).as("x").isEqualTo(x + 1);
  }

  @Test
  void decrementXOnFacingWest() {
    position = new InMemoryPosition(room, WEST, x, y);
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
    RobotPosition positionAtRightBorderFacingIt = new InMemoryPosition(room, EAST, roomSize, y);

    assertThatThrownBy(positionAtRightBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfField.class);
  }

  @Test
  void notLeaveFieldOnTheLeft() {
    RobotPosition positionAtLeftBorderFacingIt = new InMemoryPosition(room, WEST, 0, y);

    assertThatThrownBy(positionAtLeftBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfField.class);
  }

  @Test
  void notLeaveFieldOnTheTop() {
    RobotPosition positionAtTopBorderFacingIt = new InMemoryPosition(room, NORTH, x, roomSize);

    assertThatThrownBy(positionAtTopBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfField.class);
  }

  @Test
  void notLeaveFieldOnTheBottom() {
    RobotPosition positionAtBottomBorderFacingIt = new InMemoryPosition(room, SOUTH, x, 0);

    assertThatThrownBy(positionAtBottomBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfField.class);
  }
}
