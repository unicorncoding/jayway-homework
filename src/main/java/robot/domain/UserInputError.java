package robot.domain;

public class UserInputError extends IllegalArgumentException {
  public UserInputError(String s) {
    super(s);
  }

  public void writeTo(Report report) {
    report.accept(this);
  }
}
