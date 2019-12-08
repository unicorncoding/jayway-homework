package robot.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static robot.domain.ValidRoomDimensions.DIMENSION_SEPARATOR;

class ValidRoomDimensionsShould {

  int width = 5;
  int height = 5;

  String validStringInput = width + DIMENSION_SEPARATOR + height;

  @Test
  void beCreatedFromValidInput() {
    ValidRoomDimensions validRoomDimensions = new ValidRoomDimensions(validStringInput);

    assertThat(validRoomDimensions.width).as("dimensions.width").isEqualTo(width);
    assertThat(validRoomDimensions.height).as("dimensions.height").isEqualTo(height);
  }

  @Test
  void notCreatedFromInvalidStringWithLetters() {
    String invalidInput = "a c";

    assertThatThrownBy(() -> new ValidRoomDimensions(invalidInput))
        .isInstanceOf(InvalidRoomDimensions.class);
  }

  @Test
  void notCreatedFromInvalidStringWithNegativeNumbers() {
    String invalidInput = "-5 5";

    assertThatThrownBy(() -> new ValidRoomDimensions(invalidInput))
            .isInstanceOf(InvalidRoomDimensions.class);
  }
}
