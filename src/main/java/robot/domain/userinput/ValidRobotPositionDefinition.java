package robot.domain.userinput;

import robot.domain.RobotPosition.Direction;
import robot.domain.errors.InvalidRobotPositionDefinition;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static robot.infra.State.ensure;

public class ValidRobotPositionDefinition {
  private static final int xIndex = 0;
  private static final int yIndex = 1;
  private static final int directionIndex = 2;
  private static final String possibleDirectionDisplayString = directionsAsDisplayString();
  public static final String INPUT_SEPARATOR = " ";

  private static String directionsAsDisplayString() {
    return Arrays.stream(Direction.values())
        .map(direction -> direction.displayName)
        .collect(Collectors.joining());
  }

  public final int x;
  public final int y;
  public final Direction direction;

  public static ValidRobotPositionDefinitionInput inputDefinition() {
    return new ValidRobotPositionDefinition.ValidRobotPositionDefinitionInput();
  }

  Pattern pattern = Pattern.compile("[0-9]* [0-9]* [" + possibleDirectionDisplayString + "]");

  public ValidRobotPositionDefinition(String input) {
    ensure(pattern.matcher(input).matches(), InvalidRobotPositionDefinition::new);
    String[] inputSymbols = input.split(INPUT_SEPARATOR);
    this.x = coordinate(xIndex, inputSymbols);
    this.y = coordinate(yIndex, inputSymbols);
    this.direction = direction(inputSymbols);
  }

  private Direction direction(String[] inputSymbols) {
    return Direction.fromDisplayString(inputSymbols[directionIndex]);
  }

  private Integer coordinate(int dimensionIndex, String[] inputSymbols) {
    return Integer.valueOf(inputSymbols[dimensionIndex]);
  }

  static class ValidRobotPositionDefinitionInput
      implements UserInputDefinition<ValidRoomDimensions> {
    @Override
    public String describeInputFormat() {
      return "Please enter robot position in format: X Y Direction 1 letter. For example: \"5 5 N\" to place robot at x=5 and y=5 and facing North";
    }
  }
}
