package robot.domain;

import java.util.regex.Pattern;

import static robot.infra.State.ensure;

public class ValidRoomDimensions {

  public static final int widthIndex = 0;
  public static final int heightIndex = 1;
  public static final String DIMENSION_SEPARATOR = " ";
  public final int width;
  public final int height;

  public static UserInputDefinition<ValidRoomDimensions> inputDefinition() {
    return new ValidRoomDimensionsDefinition();
  }

  Pattern pattern = Pattern.compile("[0-9]* [0-9]*");

  public ValidRoomDimensions(String input) {
    ensure(pattern.matcher(input).matches(), InvalidRoomDimensions::new);
    String[] widthAndHeight = input.split(DIMENSION_SEPARATOR);
    this.width = dimension(widthIndex, widthAndHeight);
    this.height = dimension(heightIndex, widthAndHeight);
  }

  private Integer dimension(int dimensionIndex, String[] widthAndHeight) {
    return Integer.valueOf(widthAndHeight[dimensionIndex]);
  }

  static class ValidRoomDimensionsDefinition implements UserInputDefinition<ValidRoomDimensions> {
    @Override
    public String describeInputFormat() {
      return "Please enter room dimensions in format: Width Height. For example: 5 5";
    }
  }
}
