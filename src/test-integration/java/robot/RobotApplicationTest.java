package robot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

class RobotApplicationTest {
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

    user.input("50 50");
    user.input("10 20 N");
    user.input("RFRFFRFRF");

    user.input("50 50");
    user.input("10 20 N");
    user.input("RFRFFRFR");

    await()
        .atMost(2, SECONDS)
        .untilAsserted(
            () ->
                assertThat(robotApplicationOutput())
                    .contains("Report: 10 19 N", "Report: 10 18 N"));
    thread.interrupt();
  }

  private String robotApplicationOutput() {
    return new String(out.toByteArray());
  }
}
