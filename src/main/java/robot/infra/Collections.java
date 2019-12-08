package robot.infra;

import java.util.ArrayList;
import java.util.Arrays;

public class Collections {
  public static <T> ArrayList<T> newArrayList(T... elemets) {
    return new ArrayList<>(Arrays.asList(elemets));
  }
}
