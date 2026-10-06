package frc.robot.subsystems.Feeder;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Feeder extends SubsystemBase {
  private final TalonFX motor1;

  private TalonFXConfiguration motor1Config;

  private final DigitalInput dIO1;
  
  private Feeder() {
    motor1 = new TalonFX(PortMap.Feeder.MOTOR1);

    config();

    dIO1 = new DigitalInput(PortMap.Feeder.DIO1);
  }

  private void config() {
    motor1Config = new TalonFXConfiguration();
    motor1Config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    motor1Config.Feedback.SensorToMechanismRatio = FeederConstants.MOTOR_1_GEAR;
    motor1Config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    motor1Config.CurrentLimits.StatorCurrentLimit = FeederConstants.STATOR_CURRENT_LIMIT;
    motor1Config.CurrentLimits.StatorCurrentLimitEnable = true;
    motor1Config.TorqueCurrent.PeakForwardTorqueCurrent = FeederConstants.PICK_CURRENT_LIMIT;
    motor1Config.TorqueCurrent.PeakReverseTorqueCurrent = FeederConstants.PICK_CURRENT_LIMIT;

    motor1.getConfigurator().apply(motor1Config);
  }

  public void setMotorVoltage(double voltage) {
    motor1.setVoltage(voltage);
  }

  public boolean isCartridgeEmpty() {
    return dIO1.get();
  }

  @Override
  public void periodic() {
    
  }

  private static Feeder feeder;
  public static Feeder getInstance() {
    if (feeder == null) {
      feeder = new Feeder();
    }
    return feeder;
  }
}
