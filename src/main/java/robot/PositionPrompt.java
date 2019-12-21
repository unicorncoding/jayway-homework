package robot;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

class PositionPrompt {

  private final Pattern promptPattern = Pattern.compile("(?<x>[0-9]+) (?<y>[0-9]+) (?<z>[NESW])");
  private final String promptText =
          "Please enter robot position in format: X Y Z. " +
                  "For example: \"5 5 N\" to place robot at x=5 and y=5 and facing North";

  private final Scanner scanner;
  private final PrintStream printer;

  public PositionPrompt(Scanner scanner, PrintStream printer) {
    this.scanner = scanner;
    this.printer = printer;
  }

  public Position prompt(Room room) {
    printer.println(promptText);

    String input = scanner.nextLine();
    Matcher inputMatcher = promptPattern.matcher(input);
    if (!inputMatcher.matches()) {
      throw new IllegalArgumentException(format("Position [%s] is malformed. %s", input, promptText));
    }

    int x = parseInt(inputMatcher.group("x"));
    int y = parseInt(inputMatcher.group("y"));
    Direction direction = direction(inputMatcher.group("z"));

    return new Position(room, direction, x, y);
  }

  private Direction direction(String directionCommand) {
    switch (directionCommand) {
      case "N":
        return Direction.NORTH;
      case "E":
        return Direction.EAST;
      case "S":
        return Direction.SOUTH;
      case "W":
        return Direction.WEST;
      default:
        throw new IllegalArgumentException(format("Unknown direction [%s]", directionCommand));
    }
  }


}
