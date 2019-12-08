package robot.domain.userinput;

import org.junit.jupiter.api.Test;
import robot.domain.errors.InvalidRobotPositionDefinition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static robot.domain.RobotPosition.Direction.NORTH;
import static robot.domain.userinput.ValidRobotPositionDefinition.INPUT_SEPARATOR;

class ValidRobotPositionDefinitionShould {

  int x = 5;
  int y = 5;
  String direction = NORTH.displayName;

  String validInputString = x + INPUT_SEPARATOR + y + INPUT_SEPARATOR + direction;

  @Test
  void beCreatedFromValidInputString() {
    ValidRobotPositionDefinition validRobotPositionDefinition =
        new ValidRobotPositionDefinition(validInputString);

    assertThat(validRobotPositionDefinition.x).as("X").isEqualTo(x);
    assertThat(validRobotPositionDefinition.y).as("Y").isEqualTo(y);
    assertThat(validRobotPositionDefinition.direction).as("Direction").isEqualTo(NORTH);
  }

  @Test
  void notCreatedFromInputStringWithInvalidDirectionDisplaySymbol() {
    String invalidInputString = "5 5 Z";

    assertThatThrownBy(() -> new ValidRobotPositionDefinition(invalidInputString))
        .isInstanceOf(InvalidRobotPositionDefinition.class);
  }

  @Test
  void notCreatedFromInputStringWithInvalidPositions() {
    String invalidInputString = "A -5 N";

    assertThatThrownBy(() -> new ValidRobotPositionDefinition(invalidInputString))
        .isInstanceOf(InvalidRobotPositionDefinition.class);
  }
}
