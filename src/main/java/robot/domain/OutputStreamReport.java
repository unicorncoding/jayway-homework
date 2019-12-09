package robot.domain;

import robot.domain.userinput.UserInputDefinition;

import java.io.PrintStream;

public class OutputStreamReport implements Report {
  private static final String positionReportTemplate = "Report: %s %s %s";
  private final PrintStream out;

  public OutputStreamReport(PrintStream out) {
    this.out = out;
  }

  @Override
  public void accept(RobotPosition position) {
    out.println(
        String.format(
            positionReportTemplate, position.x(), position.y(), position.direction().displayName));
  }

  @Override
  public void accept(UserInputError inputError) {
    out.println(inputError.getMessage());
  }

  @Override
  public void accept(UserInputDefinition<?> inputDefinition) {
    out.println(inputDefinition.describeInputFormat());
  }
}
