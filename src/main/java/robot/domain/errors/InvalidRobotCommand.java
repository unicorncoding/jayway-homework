package robot.domain.errors;

import robot.domain.userinput.UserInputError;

public class InvalidRobotCommand extends IllegalArgumentException implements UserInputError {}
