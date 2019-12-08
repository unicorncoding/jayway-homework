package robot.domain;

import robot.domain.Report;

public class UserInputError extends IllegalArgumentException {
  public void writeTo(Report report) {
    report.accept(this);
  }
}
