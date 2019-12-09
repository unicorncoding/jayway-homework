package robot.infra;

import java.util.ArrayList;
import java.util.Arrays;

public class Collections {
  @SafeVarargs
  public static <T> ArrayList<T> newArrayList(T... elements) {
    return new ArrayList<>(Arrays.asList(elements));
  }
}
