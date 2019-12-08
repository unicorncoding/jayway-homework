package robot.domain;

import org.junit.jupiter.api.Test;
import robot.domain.RobotPosition.Direction;
import robot.domain.errors.InvalidRobotDirection;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RobotPositionDirectionShould {

  @Test
  void correctlyResolveDisplayName() {
    allDirections()
        .forEach(
            direction -> {
              Direction resolvedDirection = Direction.fromDisplayName(direction.displayName);
              assertThat(resolvedDirection).as("resolved direction").isEqualTo(direction);
            });
  }

  private Stream<Direction> allDirections() {
    return Arrays.stream(Direction.values());
  }

  @Test
  void throwOnResolveWrongDirection() {
    String invalidDirectionDisplayName = "B";
    assertThatThrownBy(() -> Direction.fromDisplayName(invalidDirectionDisplayName))
        .isInstanceOf(InvalidRobotDirection.class);
  }
}
