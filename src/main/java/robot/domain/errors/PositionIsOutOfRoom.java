package robot.domain.errors;

import robot.domain.userinput.UserInputError;

public class PositionIsOutOfRoom extends IllegalStateException implements UserInputError {}
