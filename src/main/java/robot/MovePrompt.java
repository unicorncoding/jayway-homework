package robot;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

class MovePrompt {

  private final Scanner scanner;
  private final PrintStream printer;

  public MovePrompt(Scanner scanner, PrintStream printer) {
    this.scanner = scanner;
    this.printer = printer;
  }

  public List<Move> prompt() {
    printer.println("Please enter robot command sequence: \n"
            + "L Turn left\n"
            + "R Turn right\n"
            + "F Walk forward\n"
            + "Example:\n"
            + "LFFRFRFRFF");

    String input = scanner.nextLine();

    Stream<String> moveCommands = Arrays.stream(input.split(""));
    return moveCommands
            .map(this::toMove)
            .collect(toList());
  }

  private Move toMove(String moveCommand) {
    switch (moveCommand) {
      case "F":
        return Move.FORWARD;
      case "L":
        return Move.LEFT;
      case "R":
        return Move.RIGHT;
    }
    throw new IllegalArgumentException(format("Unknown robot move [%s]", moveCommand));
  }


}
