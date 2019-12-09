package robot.domain.userinput;

import robot.domain.Report;
import robot.domain.UserCommandFactory;
import robot.domain.UserInputError;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

public class CmdUserInput implements RobotData {
  private final Scanner scanner;
  private final UserCommandFactory userCommandFactory;
  private final Report report;

  public CmdUserInput(Scanner scanner, UserCommandFactory userCommandFactory, Report report) {
    this.scanner = scanner;
    this.userCommandFactory = userCommandFactory;
    this.report = report;
  }

  @Override
  public ValidRoomDimensions roomDimensions() {
    return readUserInputTillCorrectProvided(
        ValidRoomDimensions::inputDefinition, userCommandFactory::roomDimensions);
  }

  @Override
  public ValidRobotPositionDefinition positionDefinition() {
    return readUserInputTillCorrectProvided(
        ValidRobotPositionDefinition::inputDefinition, userCommandFactory::positionDefinition);
  }

  @Override
  public ValidRobotCommandSequence commandSequence() {
    return readUserInputTillCorrectProvided(
        ValidRobotCommandSequence::inputDefinition, userCommandFactory::commandSequence);
  }

  private <T> T readUserInputTillCorrectProvided(
      Supplier<UserInputDefinition<T>> readRequestInfo, Function<String, T> mapper) {
    do {
      try {
        report.accept(readRequestInfo.get());
        String userInputString = scanner.nextLine().trim();
        return mapper.apply(userInputString);
      } catch (UserInputError e) {
        e.writeTo(report);
      }
    } while (true);
  }
}
