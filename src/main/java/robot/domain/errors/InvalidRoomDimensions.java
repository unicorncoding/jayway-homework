package robot.domain.errors;

import robot.domain.userinput.UserInputError;

public class InvalidRoomDimensions extends IllegalStateException implements UserInputError {}
