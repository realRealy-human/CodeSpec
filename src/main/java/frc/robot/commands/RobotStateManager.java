package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotStateManager extends InstantCommand {
  public static enum RobotState {
    IDLE,
    INTAKE,
    HOLD,
    SHOOTING,
    EJECT,
    OPEN_WALLS,
  }
  private static RobotState robotState = RobotState.IDLE;

  public static RobotState getRobotState() {
    return robotState;
  }

  public RobotStateManager(RobotState robotState) {
    RobotStateManager.robotState = robotState;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void initialize() {
  }
}
