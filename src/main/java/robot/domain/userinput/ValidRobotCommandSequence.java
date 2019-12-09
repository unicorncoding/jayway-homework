package robot.domain.userinput;

import robot.domain.RobotCommand;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidRobotCommandSequence {
  private static final String possibleCommandDisplayNames = possibleCommandDisplayNames();

  private static String possibleCommandDisplayNames() {
    return Arrays.stream(RobotCommand.values())
        .map(direction -> direction.displayName)
        .collect(Collectors.joining());
  }

  private final List<RobotCommand> commands;

  public ValidRobotCommandSequence(String validCommandSequence) {
    commands =
        Arrays.stream(validCommandSequence.split(""))
            .map(RobotCommand::fromDisplayName)
            .collect(Collectors.toList());
  }

  public static UserInputDefinition<ValidRobotCommandSequence> inputDefinition() {
    return new ValidCommandSequenceDefinitionInput();
  }

  public Stream<RobotCommand> stream() {
    return commands.stream();
  }

  static class ValidCommandSequenceDefinitionInput
      implements UserInputDefinition<ValidRobotCommandSequence> {
    @Override
    public String describeInputFormat() {
      return "Please enter robot command sequence: \n"
          + "L Turn left\n"
          + "R Turn right\n"
          + "F Walk forward\n"
          + "Example:\n"
          + "LFFRFRFRFF";
    }
  }
}
