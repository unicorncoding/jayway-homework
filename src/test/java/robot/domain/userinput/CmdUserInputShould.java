package robot.domain.userinput;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robot.domain.Report;
import robot.domain.UserInputError;
import robot.domain.userinput.ValidRobotCommandSequence.ValidCommandSequenceDefinitionInput;
import robot.domain.userinput.ValidRobotPositionDefinition.ValidRobotPositionDefinitionInput;
import robot.domain.userinput.ValidRoomDimensions.ValidRoomDimensionsDefinition;
import robot.infra.TestSubject;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CmdUserInputShould {

  String validInput = "valid_input";
  String invalidInput = "invalid_input";

  Scanner scanner = mock(Scanner.class);
  UserCommandFactory userCommandFactory = mock(UserCommandFactory.class);
  Report report = mock(Report.class);

  @TestSubject CmdUserInput userInput = new CmdUserInput(scanner, userCommandFactory, report);

  @BeforeEach
  void setUp() {
    when(scanner.nextLine()).thenReturn(validInput);

    setupValidInputResults();

    setupErrorsOnInvalidInput();
  }

  private void setupErrorsOnInvalidInput() {
    when(userCommandFactory.roomDimensions(invalidInput)).thenThrow(UserInputError.class);
    when(userCommandFactory.positionDefinition(invalidInput)).thenThrow(UserInputError.class);
    when(userCommandFactory.commandSequence(invalidInput)).thenThrow(UserInputError.class);
  }

  private void setupValidInputResults() {
    when(userCommandFactory.roomDimensions(validInput)).thenReturn(mock(ValidRoomDimensions.class));
    when(userCommandFactory.positionDefinition(validInput))
        .thenReturn(mock(ValidRobotPositionDefinition.class));
    when(userCommandFactory.commandSequence(validInput))
        .thenReturn(mock(ValidRobotCommandSequence.class));
  }

  @Test
  void returnValidRoomDimensionsFromCmdInput() {
    ValidRoomDimensions validRoomDimensions = userInput.roomDimensions();

    assertInputFormatForRoomDimensionsReported();
    assertThat(validRoomDimensions).as("validRoomDimensions").isNotNull();
  }

  private void assertInputFormatForRoomDimensionsReported() {
    verify(report, only()).accept(any(ValidRoomDimensionsDefinition.class));
  }

  @Test
  void keepRequestingValidRoomDimensionsFromCmdInputOnInvalidInput() {
    when(scanner.nextLine()).thenReturn(invalidInput, validInput);
    ValidRoomDimensions validRoomDimensions = userInput.roomDimensions();

    verifyPromtsUserForCorrectInput2Times();
    verifyReportsError();

    assertThat(validRoomDimensions).as("validRoomDimensions").isNotNull();
  }

  @Test
  void returnValidPositionFromCmdInput() {
    ValidRobotPositionDefinition validRobotPositionDefinition = userInput.positionDefinition();

    assertInputFormatForPositionReported();
    assertThat(validRobotPositionDefinition).as("validRobotPositionDefinition").isNotNull();
  }

  private void assertInputFormatForPositionReported() {
    verify(report, only()).accept(any(ValidRobotPositionDefinitionInput.class));
  }

  @Test
  void keepRequestingValidPositionDefinitionFromCmdInputOnInvalidInput() {
    when(scanner.nextLine()).thenReturn(invalidInput, validInput);
    ValidRobotPositionDefinition positionDefinition = userInput.positionDefinition();

    verifyPromtsUserForCorrectInput2Times();
    verifyReportsError();

    assertThat(positionDefinition).as("positionDefinition").isNotNull();
  }

  @Test
  void returnValidCommandSequenceFromCmdInput() {
    ValidRobotCommandSequence commandSequence = userInput.commandSequence();

    assertInputFormatForCommandSequenceReported();
    assertThat(commandSequence).as("commandSequence").isNotNull();
  }

  private void assertInputFormatForCommandSequenceReported() {
    verify(report, only()).accept(any(ValidCommandSequenceDefinitionInput.class));
  }

  @Test
  void keepRequestingCommandSequenceFromCmdInputOnInvalidInput() {
    when(scanner.nextLine()).thenReturn(invalidInput, validInput);
    ValidRobotCommandSequence commandSequence = userInput.commandSequence();

    verifyPromtsUserForCorrectInput2Times();
    verifyReportsError();

    assertThat(commandSequence).as("positionDefinition").isNotNull();
  }

  private void verifyPromtsUserForCorrectInput2Times() {
    verify(report, times(2)).accept(any(UserInputDefinition.class));
  }

  private void verifyReportsError() {
    verify(report, times(1)).accept(any(UserInputError.class));
  }
}
