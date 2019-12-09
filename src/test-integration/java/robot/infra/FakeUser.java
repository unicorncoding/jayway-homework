package robot.infra;

import robot.domain.RobotCommand;
import robot.domain.RobotPosition;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FakeUser {
  private QueuedInput in = new QueuedInput();

  public FakeUser() throws IOException {}

  public InputStream in() {
    return in;
  }

  private void input(String data) {
    try {
      in.add(data + "\n");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void inputRobotPosition(int x, int y, RobotPosition.Direction direction) {
    input(x + " " + y + " " + direction.displayName);
  }

  public void inputRobotCommands(RobotCommand... commands) {
    String commandsAsString =
        Arrays.stream(commands).map(command -> command.displayName).collect(Collectors.joining());
    input(commandsAsString);
  }

  public void inputRoomDimensions(int width, int height) {
    input(width + " " + height);
  }

  static class QueuedInput extends InputStream {
    LinkedBlockingQueue<Byte> data = new LinkedBlockingQueue<>();

    public void add(String data) throws InterruptedException {
      for (byte b : data.getBytes()) {
        this.data.put(b);
      }
    }

    @Override
    public int read() throws IOException {
      try {
        Byte poll = data.poll(1, TimeUnit.SECONDS);
        return poll == null ? -1 : poll;
      } catch (InterruptedException e) {
        return -1;
      }
    }
  }
}
