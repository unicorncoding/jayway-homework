package robot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RobotApplicationTest {

  private FakeOutput output = new FakeOutput();
  private FakeInput input = new FakeInput();

  @Test
  void reportsItsLocationAfterFollowingASeriesOfMovesDefinedInReadmeExample1() {
    //  👉 given:
    //  o o o o o
    //  ↑ o o o o
    //  o o o o o
    //  o o o o o
    //  o o o o o
    input.roomDimensions(5, 5);
    input.robotPosition(1, 2, "N");

    // and:
    input.robotMoves("RFRFFRFRF");

    //  👉 when:
    robotRuns();

    //  👉 then:
    //  o o o o o
    //  o o o o o
    //  ↑ o o o o
    //  o o o o o
    //  o o o o o
    assertThat(output).asString().contains("Report: 1 3 N");
  }

  @Test
  void reportsItsLocationAfterFollowingASeriesOfMovesDefinedInReadmeExample2() {
    //  👉 given:
    //  → o o o o
    //  o o o o o
    //  o o o o o
    //  o o o o o
    //  o o o o o
    input.roomDimensions(5, 5);
    input.robotPosition(1, 1, "E");

    // and:
    input.robotMoves("RFLFFLRF");

    //  👉 when:
    robotRuns();

    //  👉 then:
    //  o o o o o
    //  o o o → o
    //  o o o o o
    //  o o o o o
    //  o o o o o
    assertThat(output).asString().contains("Report: 4 2 E");
  }

  @Test
  void reportsItsInitialLocationAfterMovingAllTheWayAroundTheEdgeClockwise() {
    //  👉 given:
    //  ↑ o
    //  o o
    input.roomDimensions(2, 2);
    input.robotPosition(1, 1, "N");

    // and:
    input.robotMoves("RFRFRFRF");

    //  👉 when:
    robotRuns();

    //  👉 then:
    //  ↑ o
    //  o o
    assertThat(output).asString().contains("Report: 1 1 N");
  }

  @Test
  void reportsItsInitialLocationAfterMovingAllTheWayAroundTheEdgeCounterClockwise() {
    //  👉 given:
    //  ← o
    //  o o
    input.roomDimensions(2, 2);
    input.robotPosition(1, 1, "W");

    // and:
    input.robotMoves("LFLFLFLF");

    //  👉 when:
    robotRuns();

    //  👉 then:
    //  ↑ o
    //  o o
    assertThat(output).asString().contains("Report: 1 1 W");
  }

  @Test
  void throwsOnUnknownMove() {
    input.roomDimensions(5, 5);
    input.robotPosition(1, 1, "N");
    input.robotMoves("X");

    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, this::robotRuns);
    assertThat(e).hasMessage("Unknown robot move [X]");
  }

  @Test
  void throwsOnWrongPosition() {
    input.roomDimensions(5, 5);
    input.robotPosition(1, 1, "X");
    input.robotMoves("F");

    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, this::robotRuns);
    assertThat(e).hasMessage("Position [1 1 X] is malformed. " +
            "Please enter robot position in format: X Y Z. " +
            "For example: \"5 5 N\" to place robot at x=5 and y=5 and facing North");
  }

  @Test
  void throwsOnWrongDimensions() {
    input.roomDimensions(-1, -1);
    input.robotPosition(1, 1, "N");
    input.robotMoves("F");

    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, this::robotRuns);
    assertThat(e).hasMessage("Room dimensions [-1 -1] contains invalid chars.");
  }



  @ParameterizedTest
  @MethodSource("robotPosition")
  void throwsOnUnknownMove(int x, int y) {
    input.roomDimensions(5, 5);
    input.robotPosition(x, y, "N");
    input.robotMoves("RFRFFRFRF");

    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, this::robotRuns);
    assertThat(e).hasMessage(
            "Robot can't be placed/move outside room. " +
                    "Room dimensions are [5, 5], but Robot placed at [" + x + " " + y + "]");
  }

  static Stream<Arguments> robotPosition() {
    return Stream.of(
            arguments(0, 1),
            arguments(1, 0),
            arguments(1, 6),
            arguments(6, 1)
    );
  }

  private void robotRuns() {
    RobotApplication app = new RobotApplication(input.stream(), output.stream());
    app.run();
  }


}
