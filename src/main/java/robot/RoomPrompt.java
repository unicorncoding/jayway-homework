package robot;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

class RoomPrompt {

  private final Pattern pattern = Pattern.compile("[0-9]* [0-9]*");
  private final Scanner scanner;
  private final PrintStream out;

  public RoomPrompt(Scanner scanner, PrintStream out) {
    this.scanner = scanner;
    this.out = out;
  }

  private Integer dimension(int dimensionIndex, String[] widthAndHeight) {
    return Integer.valueOf(widthAndHeight[dimensionIndex]);
  }

  public Room prompt() {
    out.println("Please enter room dimensions in format: Width Height. For example: 5 5");

    String input = scanner.nextLine();
    Matcher inputMatcher = pattern.matcher(input);
    if (!inputMatcher.matches()) {
      throw new IllegalArgumentException(format("Room dimensions [%s] contains invalid chars.", input));
    }

    String[] widthAndHeight = input.split(" ");

    return new Room(
            dimension(0, widthAndHeight),
            dimension(1, widthAndHeight)
    );

  }


}
