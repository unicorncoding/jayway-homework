package robot;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

class FakeInput {

  private final List<String> lines = new ArrayList<>();

  void robotPosition(int x, int y, String direction) {
    lines.add(x + " " + y + " " + direction);
  }

  void robotMoves(String allMoves) {
    lines.add(allMoves);
  }

  void roomDimensions(int width, int height) {
    lines.add(width + " " + height);
  }

  InputStream stream() {
    String newLine = System.getProperty("line.separator");
    String input = String.join(newLine, lines);
    return new ByteArrayInputStream(input.getBytes(UTF_8));
  }

}
