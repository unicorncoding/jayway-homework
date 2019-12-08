package robot.domain;

import robot.domain.userinput.UserInputDefinition;

public interface Report {
  void accept(RobotPosition position);

  void accept(UserInputError inputError);

  void accept(UserInputDefinition<?> inputDefinition);
}
