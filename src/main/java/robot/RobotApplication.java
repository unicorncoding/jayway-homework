package robot;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import java.util.Scanner;

public class RobotApplication {

  private final Scanner scanner;
  private final PrintStream printer;

  private final RoomPrompt roomPrompt;
  private final PositionPrompt positionPrompt;
  private final MovePrompt movePrompt;

  public RobotApplication(InputStream in, OutputStream out) {
    this.scanner = new Scanner(in);
    this.printer = new PrintStream(out);
    this.roomPrompt = new RoomPrompt(scanner, printer);
    this.positionPrompt = new PositionPrompt(scanner, printer);
    this.movePrompt = new MovePrompt(scanner, printer);
  }

  public static void main(String... args) {
    RobotApplication app = new RobotApplication(System.in, System.out);
    app.run();
  }

  public void run() {
    Room room = roomPrompt.prompt();
    Position position = positionPrompt.prompt(room);
    List<Move> moves = movePrompt.prompt();

    Robot robot = new Robot(position);
    robot.perform(moves);
    robot.report(printer);
  }

}
