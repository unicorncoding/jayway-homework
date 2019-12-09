package robot;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FakeUser {
  private QueuedInput in = new QueuedInput();

  public FakeUser() throws IOException {}

  InputStream in() {
    return in;
  }

  void input(String data) {
    try {
      in.add(data + "\n");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
