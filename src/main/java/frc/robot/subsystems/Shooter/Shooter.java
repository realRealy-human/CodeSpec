package frc.robot.subsystems.Shooter;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Shooter extends SubsystemBase {
  private final TalonFX master;
  private final TalonFX slave;
  
  private final StatusSignal<AngularVelocity> masterSpeed;
  private final StatusSignal<AngularVelocity> slaveSpeed;

  private TalonFXConfiguration masterConfig;
  private TalonFXConfiguration slaveConfig;

  private final StrictFollower motorFollower;
  
  private Shooter() {
    master = new TalonFX(PortMap.Shooter.MASTER);
    slave = new TalonFX(PortMap.Shooter.SLAVE);

    masterSpeed = master.getVelocity();
    slaveSpeed = slave.getVelocity();

    config();

    motorFollower = new StrictFollower(master.getDeviceID());
  }

  private void config() {
    masterConfig = new TalonFXConfiguration();
    masterConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    masterConfig.Feedback.SensorToMechanismRatio = ShooterConstants.MOTOR_1_GEAR;

    masterConfig.CurrentLimits.StatorCurrentLimit = ShooterConstants.STATOR_CURRENT_LIMIT;
    masterConfig.CurrentLimits.StatorCurrentLimitEnable = true;

    masterConfig.Slot0.kP = ShooterConstants.KP;

    master.getConfigurator().apply(masterConfig);
    
    slaveConfig = new TalonFXConfiguration();
    slaveConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    slaveConfig.Feedback.SensorToMechanismRatio = ShooterConstants.MOTOR_2_GEAR;

    slaveConfig.CurrentLimits.StatorCurrentLimit = ShooterConstants.STATOR_CURRENT_LIMIT;
    slaveConfig.CurrentLimits.StatorCurrentLimitEnable = true;

    slaveConfig.Slot0.kP = ShooterConstants.KP;

    slave.getConfigurator().apply(slaveConfig);
  }

  public void setMotorsVoltage(double voltage) {
    master.setVoltage(voltage);
    slave.setControl(motorFollower);
  }

  public double getmasterSpeed() {
    return masterSpeed.getValueAsDouble() * 60;
  }
  public double getslaveSpeed() {
    return slaveSpeed.getValueAsDouble() * 60;
  }

  @Override
  public void periodic() {
    StatusSignal.refreshAll(masterSpeed, slaveSpeed);
  }

  private static Shooter shooter;
  public static Shooter getInstance() {
    if (shooter == null) {
      shooter = new Shooter();
    }
    return shooter;
  }
}
