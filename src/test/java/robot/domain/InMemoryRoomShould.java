package robot.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemoryRoomShould {

  @Test
  void beCreatedWithPositiveWidthAndHeight() {
    int width = 10;
    int height = 7;
    InMemoryRoom room = new InMemoryRoom(width, height);

    assertThat(room.width()).as("width").isEqualTo(width);
    assertThat(room.height()).as("height").isEqualTo(height);
  }

  @Test
  void notCreatedWithPositiveWidthAndNegativeHeight() {
    int width = 10;
    int height = -7;
    assertThatThrownBy(() -> new InMemoryRoom(width, height))
        .isInstanceOf(NegativeRoomDimensions.class);
  }

  @Test
  void notCreatedWithNegativeWidthAndPositiveHeight() {
    int width = -10;
    int height = 7;

    assertThatThrownBy(() -> new InMemoryRoom(width, height))
        .isInstanceOf(NegativeRoomDimensions.class);
  }
}
