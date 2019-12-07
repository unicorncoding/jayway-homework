package robot.domain;

public interface RobotPosition {
    void moveForward();

    void turnLeft();

    void turnRight();

    boolean isInside(Field field);
}
