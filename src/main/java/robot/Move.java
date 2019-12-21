package robot;

import java.util.function.Consumer;

enum Move {

  FORWARD(Position::forward),

  LEFT(Position::left),

  RIGHT(Position::right);

  final Consumer<Position> onMove;

  Move(Consumer<Position> onMove) {
    this.onMove = onMove;
  }

  void perform(Position position) {
    onMove.accept(position);
  }

}
