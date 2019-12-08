package robot.domain.errors;

import robot.domain.userinput.UserInputError;

public class InvalidRobotPositionDefinition extends IllegalStateException
    implements UserInputError {}
