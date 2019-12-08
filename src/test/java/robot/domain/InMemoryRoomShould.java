package robot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.FieldSetter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class InMemoryRoomShould {
  int width = 10;
  int height = 7;
  ValidRoomDimensions dimensions = mock(ValidRoomDimensions.class);

  @BeforeEach
  void setUp() throws NoSuchFieldException {
    FieldSetter.setField(dimensions, ValidRoomDimensions.class.getDeclaredField("width"), width);
    FieldSetter.setField(dimensions, ValidRoomDimensions.class.getDeclaredField("height"), height);
  }

  @Test
  void beCreatedFromValidRoomDimensions() {
    InMemoryRoom room = new InMemoryRoom(dimensions);

    assertThat(room.width()).as("width").isEqualTo(width);
    assertThat(room.height()).as("height").isEqualTo(height);
  }
}
