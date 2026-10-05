package frc.robot.subsystems.Shooter;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Shooter extends SubsystemBase {
  private final TalonFX master;
  private final TalonFX slave;
  
  private final StatusSignal<AngularVelocity> velocity;

  private TalonFXConfiguration masterConfig;
  private TalonFXConfiguration slaveConfig;

  private final VelocityVoltage control;

  private final StrictFollower motorFollower;
  
  private Shooter() {
    master = new TalonFX(PortMap.Shooter.MASTER);
    slave = new TalonFX(PortMap.Shooter.SLAVE);

    velocity = master.getVelocity();

    config();

    control = new VelocityVoltage(0);

    motorFollower = new StrictFollower(master.getDeviceID());
    slave.setControl(motorFollower);
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

  public void setTargerVelocity(double velocity) {
    master.setControl(control.withVelocity(velocity * 60).withSlot(0));
  }

  public double getVelocity() {
    return velocity.getValueAsDouble() * 60;
  }

  @Override
  public void periodic() {
    StatusSignal.refreshAll(velocity);
  }

  private static Shooter shooter;
  public static Shooter getInstance() {
    if (shooter == null) {
      shooter = new Shooter();
    }
    return shooter;
  }
}
