package robot.domain.userinput;

import org.junit.jupiter.api.Test;
import robot.domain.RobotCommand;
import robot.domain.errors.InvalidRobotCommand;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static robot.domain.RobotCommand.*;
import static robot.infra.Collections.newArrayList;

class ValidRobotCommandSequenceShould {
  String validCommandSequence = "LFR";
  List<RobotCommand> commands = newArrayList(TURN_LEFT, MOVE_FORWARD, TURN_RIGHT);

  @Test
  void beCreatedFromValidInput() {
    ValidRobotCommandSequence validRobotCommandSequence =
        new ValidRobotCommandSequence(validCommandSequence);

    List<RobotCommand> robotCommands =
        validRobotCommandSequence.stream().collect(Collectors.toList());

    assertThat(robotCommands).as("robotCommands").hasSameElementsAs(commands);
  }

  @Test
  void notCreatedFromInvalidCommandSequence() {
    String invalidCommandSequence = "F5L";
    assertThatThrownBy(() -> new ValidRobotCommandSequence(invalidCommandSequence))
        .isInstanceOf(InvalidRobotCommand.class);
  }
}
