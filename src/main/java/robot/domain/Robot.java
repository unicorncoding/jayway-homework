package robot.domain;

public class Robot {
  private final RobotPosition position;

  public Robot(Field field, RobotPosition position) {
    this.position = position;
  }

  public void positionTo(Report report) {
    report.accept(position);
  }
}
