package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Feeder.FeederState;
import frc.robot.subsystems.Hood.HoodState;
import frc.robot.subsystems.Shooter.ShooterState;

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

    setStates(robotState);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  private void setStates(RobotState robotState) {
    switch (robotState) {
      case IDLE:
        FeederCommand.getInstance(null).setState(FeederState.IDLE);
        HoodCommand.getInstance(null).setState(HoodState.IDLE);
        ShooterCommand.getInstance(null).setState(ShooterState.IDLE);
        break;
      case INTAKE:
        FeederCommand.getInstance(null).setState(FeederState.IDLE);
        HoodCommand.getInstance(null).setState(HoodState.IDLE);
        ShooterCommand.getInstance(null).setState(ShooterState.IDLE);
        break;
      case HOLD:
        FeederCommand.getInstance(null).setState(FeederState.HOLD);
        HoodCommand.getInstance(null).setState(HoodState.IDLE);
        ShooterCommand.getInstance(null).setState(ShooterState.IDLE);
        break;
      case SHOOTING:
        FeederCommand.getInstance(null).setState(FeederState.SHOOT);
        HoodCommand.getInstance(null).setState(HoodState.SHOOT);
        ShooterCommand.getInstance(null).setState(ShooterState.SHOOT);
        break;
      case EJECT:
        FeederCommand.getInstance(null).setState(FeederState.FORWARD);
        HoodCommand.getInstance(null).setState(HoodState.EJECT);
        ShooterCommand.getInstance(null).setState(ShooterState.EJECT);
        break;
      case OPEN_WALLS:
        FeederCommand.getInstance(null).setState(FeederState.IDLE);
        HoodCommand.getInstance(null).setState(HoodState.IDLE);
        ShooterCommand.getInstance(null).setState(ShooterState.IDLE);
        break;
    }
  }

  @Override
  public void initialize() {
  }
}
