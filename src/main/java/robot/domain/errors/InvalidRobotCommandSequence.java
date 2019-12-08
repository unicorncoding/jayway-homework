package robot.domain.errors;

import robot.domain.userinput.UserInputError;

public class InvalidRobotCommandSequence extends IllegalArgumentException
    implements UserInputError {}
