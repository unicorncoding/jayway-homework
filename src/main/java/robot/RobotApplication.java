package robot;

import robot.domain.*;
import robot.domain.userinput.CmdUserInput;
import robot.domain.userinput.RobotData;
import robot.domain.userinput.ValidRobotCommandSequence;

import java.util.Scanner;

public class RobotApplication {

  public static void main(String... args) {
    OutputStreamReport report = new OutputStreamReport(System.out);
    RobotData dataProvider =
        new CmdUserInput(
            new Scanner(System.in),
            UserCommandFactory.INSTANCE,
                report);

    while (true) {
      Room room = new InMemoryRoom(dataProvider.roomDimensions());
      RobotPosition robotPosition = new InMemoryPosition(room, dataProvider.positionDefinition());
      Robot robot = new Robot(robotPosition);
      ValidRobotCommandSequence validRobotCommandSequence = dataProvider.commandSequence();
      robot.complete(validRobotCommandSequence);
      robot.positionTo(report);
    }
  }
}
