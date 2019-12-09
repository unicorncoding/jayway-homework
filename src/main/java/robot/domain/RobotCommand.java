package robot.domain;

import robot.domain.errors.InvalidRobotCommand;

import java.util.Arrays;

public enum RobotCommand {
  MOVE_FORWARD("F"),
  TURN_LEFT("L"),
  TURN_RIGHT("R");

  public final String displayName;

  RobotCommand(String displayName) {
    this.displayName = displayName;
  }

  public static RobotCommand fromDisplayName(String displayName) {
    return Arrays.stream(values())
        .filter(command -> command.displayName.equals(displayName))
        .findAny()
        .orElseThrow(() -> new InvalidRobotCommand(displayName));
  }
}
