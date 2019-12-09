package robot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robot.infra.FakeUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static robot.domain.RobotCommand.MOVE_FORWARD;
import static robot.domain.RobotCommand.TURN_RIGHT;
import static robot.domain.RobotPosition.Direction.NORTH;

class RobotApplicationTest {
  public static final String FIRST_EXPECTED_ROBOT_REPORT = "Report: 10 19 N";
  public static final String SECOND_EXPECTED_ROBOT_REPORT = "Report: 10 18 N";
  FakeUser user;
  ByteArrayOutputStream out;
  PrintStream stdin = System.out;

  RobotApplicationTest() throws IOException {}

  @BeforeEach
  void setUp() throws IOException {
    out = new ByteArrayOutputStream();
    user = new FakeUser();
    interceptSystemInOut();
  }

  @AfterEach
  void tearDown() {
    System.setOut(stdin);
  }

  private void interceptSystemInOut() {
    System.setOut(new PrintStream(out));
    System.setIn(user.in());
  }

  @Test
  void successfullyProcessInput2Times() throws InterruptedException {
    Thread thread = new Thread(RobotApplication::main);
    thread.start();

    userPlacesRobotAndInputsCommands();
    userPlaceRobotAndInputsCommandsSecondTime();

    await()
        .atMost(2, SECONDS)
        .untilAsserted(
            () ->
                assertThat(robotApplicationOutput())
                    .contains(FIRST_EXPECTED_ROBOT_REPORT, SECOND_EXPECTED_ROBOT_REPORT));
    thread.interrupt();
  }

  private void userPlaceRobotAndInputsCommandsSecondTime() {
    user.inputRoomDimensions(50, 50);
    user.inputRobotPosition(10, 20, NORTH);
    user.inputRobotCommands(
        TURN_RIGHT,
        MOVE_FORWARD,
        TURN_RIGHT,
        MOVE_FORWARD,
        MOVE_FORWARD,
        TURN_RIGHT,
        MOVE_FORWARD,
        TURN_RIGHT);
  }

  private void userPlacesRobotAndInputsCommands() {
    user.inputRoomDimensions(50, 50);
    user.inputRobotPosition(10, 20, NORTH);
    user.inputRobotCommands(
        TURN_RIGHT,
        MOVE_FORWARD,
        TURN_RIGHT,
        MOVE_FORWARD,
        MOVE_FORWARD,
        TURN_RIGHT,
        MOVE_FORWARD,
        TURN_RIGHT,
        MOVE_FORWARD);
  }

  private String robotApplicationOutput() {
    return new String(out.toByteArray());
  }
}
