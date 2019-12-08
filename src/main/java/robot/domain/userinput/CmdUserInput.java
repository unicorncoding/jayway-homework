package robot.domain.userinput;

import robot.domain.userinput.UserInput;
import robot.domain.userinput.ValidRobotPositionDefinition;
import robot.domain.userinput.ValidRoomDimensions;

import java.util.Scanner;

public class CmdUserInput implements UserInput {
    private final Scanner scanner;

    public CmdUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public ValidRoomDimensions roomDimensions() {
        return null;
    }

    @Override
    public ValidRobotPositionDefinition positionDefinitions() {
        return null;
    }

    @Override
    public ValidRobotCommandSequence commandSequence() {
        return null;
    }
}
