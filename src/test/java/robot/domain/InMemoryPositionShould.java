package robot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.FieldSetter;
import robot.domain.RobotPosition.Direction;
import robot.domain.errors.PositionIsOutOfRoom;
import robot.domain.userinput.ValidRobotPositionDefinition;
import robot.infra.TestSubject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static robot.domain.RobotPosition.Direction.*;

class InMemoryPositionShould {
  int roomSize = 10;
  int x = inTheMiddleOfTheRoom();
  int y = inTheMiddleOfTheRoom();
  Direction direction = NORTH;

  int inTheMiddleOfTheRoom() {
    return 10 / 2;
  }

  Room room = mock(Room.class);
  ValidRobotPositionDefinition positionDefinition = positionDefinition(direction, x, y);

  @TestSubject InMemoryPosition position;

  @BeforeEach
  void setUp() {
    when(room.width()).thenReturn(roomSize);
    when(room.height()).thenReturn(roomSize);

    position = new InMemoryPosition(room, positionDefinition);
  }

  private ValidRobotPositionDefinition positionDefinition(Direction direction, int x, int y) {
    try {
      ValidRobotPositionDefinition positionDefinition = mock(ValidRobotPositionDefinition.class);
      FieldSetter.setField(
          positionDefinition, ValidRobotPositionDefinition.class.getDeclaredField("x"), x);
      FieldSetter.setField(
          positionDefinition, ValidRobotPositionDefinition.class.getDeclaredField("y"), y);
      FieldSetter.setField(
          positionDefinition,
          ValidRobotPositionDefinition.class.getDeclaredField("direction"),
          direction);
      return positionDefinition;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Failed init position definition");
    }
  }

  @Test
  void notCreatePositionOutsideField() {
    assertThatThrownBy(
            () -> new InMemoryPosition(room, positionDefinition(NORTH, roomSize + 1, roomSize)))
        .isInstanceOf(PositionIsOutOfRoom.class);
  }

  @Test
  void incrementYOnFacingNorth() {
    position.forward();

    assertThat(position.y()).as("y").isEqualTo(y + 1);
  }

  @Test
  void decrementYOnFacingSouth() throws NoSuchFieldException {
    position = new InMemoryPosition(room, positionDefinition(SOUTH, x, y));
    position.forward();

    assertThat(position.y()).as("y").isEqualTo(y - 1);
  }

  @Test
  void incrementXOnFacingEast() {
    position = new InMemoryPosition(room, positionDefinition(EAST, x, y));
    position.forward();

    assertThat(position.x()).as("x").isEqualTo(x + 1);
  }

  @Test
  void decrementXOnFacingWest() {
    position = new InMemoryPosition(room, positionDefinition(WEST, x, y));
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
    RobotPosition positionAtRightBorderFacingIt =
        new InMemoryPosition(room, positionDefinition(EAST, roomSize, y));

    assertThatThrownBy(positionAtRightBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfRoom.class);
  }

  @Test
  void notLeaveFieldOnTheLeft() {
    RobotPosition positionAtLeftBorderFacingIt =
        new InMemoryPosition(room, positionDefinition(WEST, 0, y));

    assertThatThrownBy(positionAtLeftBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfRoom.class);
  }

  @Test
  void notLeaveFieldOnTheTop() {
    RobotPosition positionAtTopBorderFacingIt =
        new InMemoryPosition(room, positionDefinition(NORTH, x, roomSize));

    assertThatThrownBy(positionAtTopBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfRoom.class);
  }

  @Test
  void notLeaveFieldOnTheBottom() {
    RobotPosition positionAtBottomBorderFacingIt =
        new InMemoryPosition(room, positionDefinition(SOUTH, x, 0));

    assertThatThrownBy(positionAtBottomBorderFacingIt::forward)
        .isInstanceOf(PositionIsOutOfRoom.class);
  }
}
