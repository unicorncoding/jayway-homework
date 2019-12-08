package robot;

import robot.domain.*;
import robot.domain.userinput.CmdUserInput;

import java.util.Scanner;

public class RobotApplication {

  public static void main(String[] args) {
    CmdUserInput cmdUserInput = new CmdUserInput(new Scanner(System.in));

    Room room = new InMemoryRoom(cmdUserInput.roomDimensions());
    RobotPosition robotPosition = new InMemoryPosition(room, cmdUserInput.positionDefinitions());
    Robot robot = new Robot(robotPosition);
  }
}
