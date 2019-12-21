package robot;

import java.io.PrintStream;
import java.util.List;

class Robot {

  private final Position position;

  public Robot(Position position) {
    this.position = position;
  }

  public void perform(List<Move> moves) {
    moves.forEach(move -> move.perform(position));
  }

  public void reportPositionUsing(PrintStream printer) {
    printer.println(position);
  }

}
