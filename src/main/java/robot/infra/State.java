package robot.infra;

import java.util.function.Supplier;

public class State {
  public static <T extends RuntimeException> void ensure(
      boolean expression, Supplier<T> throwOnError) {
    if (expression) return;
    throw throwOnError.get();
  }
}
