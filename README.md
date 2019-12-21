### Programming assignment

In order to evaluate your programming skills, we ask you to solve ONE of the following task. The task is
solved in a Java version of your choice.
We ask you to deliver the solution in the same quality as you would provide a delivery to a customer.
You may not use other people's code or code libraries to resolve the task. That is, the solution can only
contain references to the Java standard libraries, as well as any third party libraries for unity tests.
We are interested in your competence in several areas, including:

- Java in general
- Object oriented analysis and design
- Tests
- User interfaces

Your solution to the programming assignment will form the basis of the conversation during the technical
interview. Highlight what you are good at. If you feel certain parts could have been resolved better if you
had more time, then comment on that too.
From the time you receive the assignment, you usually have one week to submit your solution.
The solution can be submitted via GitHub or similar.

### Task 1: Robot programming
Your task is to program the controller to a robot. It's a simple robot that can walk around in a room where
the floor is represented as a number of fields in a wire mesh. Input is first two numbers, which tells the
robot how big the room is:

5 7

Which means that the room is 5 fields wide and is 7 fields deep.
The size of the room follows two digits and one letter indicating the starting position of the robot and its
orientation in space. For example:

3 3 N

Which means that the robot is in field (3, 3) and faces north. Subsequently, the robot receives a number of
navigation commands in the form of characters. There are three commands:

- L Turn left
- R Turn right
- F Walk forward

Example:

LFFRFRFRFF

After the last command is received, the robot must report which field it is in and what direction it is facing. Example:

5 5

1 2 N

RFRFFRFRF

Report: 1 3 N

5 5

1 1 E

RFLFFLRF

Report: 4 2 E

### Build with gradle:
./gradlew clean build

### Execute from cmd:
java -jar build/libs/RobotProgrammingHomework-1.0.jar

### Execute from IDE:
Locate class: *robot.RobotApplication*
Run main method from it