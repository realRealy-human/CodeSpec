// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.RobotStateManager;
import frc.robot.commands.RobotStateManager.RobotState;
import frc.robot.subsystems.Feeder.Feeder;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  private final PS5Controller m_driverController =
      new PS5Controller(OperatorConstants.kDriverControllerPort);
  
  public RobotContainer() {
    configureBindings();
  }

  private boolean isIntakeOpen() {
    return false; // coding the intake was not required for the project
  }
  private boolean didIntakeOpen() {
    return false; // same as isIntakeOpen()
  }
  private boolean isCartridgeFull() {
    return false; // same as isIntakeOpen()
  }
  private boolean isShiftActive() {
    return false; // same as isIntakeOpen()
  }

  private boolean isRobotState(RobotState robotState) {
    return RobotStateManager.getRobotState().equals(robotState);
  }

  private void configureBindings() {
    new Trigger(() -> m_driverController.getTouchpadButton()).onTrue(new RobotStateManager(RobotState.IDLE));
    new Trigger(() -> 
        (isRobotState(RobotState.INTAKE) && !m_driverController.getTriangleButton() && Feeder.getInstance().isCartridgeEmpty()) ||
        ((isRobotState(RobotState.EJECT) || m_driverController.getSquareButton()) && Feeder.getInstance().isCartridgeEmpty()) ||
        (isRobotState(RobotState.OPEN_WALLS) && isIntakeOpen()) ||
        ((isRobotState(RobotState.SHOOTING) || m_driverController.getCircleButton()) && Feeder.getInstance().isCartridgeEmpty())
    ).onTrue(new RobotStateManager(RobotState.IDLE));

    new Trigger(() -> m_driverController.getTriangleButton() && !isCartridgeFull()).onTrue(new RobotStateManager(RobotState.INTAKE));

    new Trigger(() -> isRobotState(RobotState.INTAKE) && 
        (!m_driverController.getTriangleButton() || isCartridgeFull()) && !Feeder.getInstance().isCartridgeEmpty()
    ).onTrue(new RobotStateManager(RobotState.HOLD));

    new Trigger(() -> m_driverController.getCircleButton() && isShiftActive() && !Feeder.getInstance().isCartridgeEmpty()).onTrue(new RobotStateManager(RobotState.SHOOTING));
    
    new Trigger(() -> m_driverController.getSquareButton() && !Feeder.getInstance().isCartridgeEmpty()).onTrue(new RobotStateManager(RobotState.EJECT));

    new Trigger(() -> !isIntakeOpen() && !didIntakeOpen()).onTrue(new RobotStateManager(RobotState.OPEN_WALLS));
  }

  public Command getAutonomousCommand() {
    return Autos.exampleAuto();//m_exampleSubsystem);
  }
}
