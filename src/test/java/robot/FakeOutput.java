package robot;

import java.io.ByteArrayOutputStream;

public class FakeOutput {

  private final ByteArrayOutputStream output = new ByteArrayOutputStream();

  public ByteArrayOutputStream stream() {
    return output;
  }

  @Override
  public String toString() {
    return output.toString();
  }
}
