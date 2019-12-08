package robot.domain;

import org.junit.jupiter.api.Test;
import robot.domain.errors.InvalidRobotCommand;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RobotCommandShould {

  @Test
  void correctlyResolveDisplayNames() {
    allCommands()
        .forEach(
            command -> {
              RobotCommand resolvedCommand = RobotCommand.fromDisplayName(command.displayName);
              assertThat(resolvedCommand).as("resolved command").isEqualTo(command);
            });
  }

    @Test
    void throwOnResolveWrongDisplayName() {
        assertThatThrownBy(() -> RobotCommand.fromDisplayName("Z"))
                .isInstanceOf(InvalidRobotCommand.class);
    }

  private Stream<RobotCommand> allCommands() {
    return Arrays.stream(RobotCommand.values());
  }
}
