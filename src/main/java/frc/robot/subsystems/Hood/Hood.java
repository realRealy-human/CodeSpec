package frc.robot.subsystems.Hood;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Hood extends SubsystemBase {
  private final TalonFX motor1;

  private TalonFXConfiguration motor1Config;

  private final PositionVoltage control;

  private final CANcoder cANcoder;

  private final StatusSignal<Angle> angle;
  
  private Hood() {
    motor1 = new TalonFX(PortMap.Feeder.MOTOR1);

    config();

    control = new PositionVoltage(0);

    cANcoder = new CANcoder(PortMap.Hood.CAN_CODER);
    angle = cANcoder.getPosition();
  }

  private void config() {
    motor1Config = new TalonFXConfiguration();
    motor1Config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    motor1Config.Feedback.SensorToMechanismRatio = HoodConstants.MOTOR_1_GEAR;
    motor1Config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    motor1Config.CurrentLimits.StatorCurrentLimit = HoodConstants.STATOR_CURRENT_LIMIT;
    motor1Config.CurrentLimits.StatorCurrentLimitEnable = true;
    motor1Config.TorqueCurrent.PeakForwardTorqueCurrent = HoodConstants.PICK_CURRENT_LIMIT;
    motor1Config.TorqueCurrent.PeakReverseTorqueCurrent = HoodConstants.PICK_CURRENT_LIMIT;

    motor1Config.Slot0.kP = HoodConstants.KP;

    motor1Config.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RemoteCANcoder;
    motor1Config.Feedback.FeedbackRemoteSensorID = cANcoder.getDeviceID();
    motor1Config.Feedback.RotorToSensorRatio = HoodConstants.CAN_CODER_TO_MOTOR_1_GEAR;

    motor1.getConfigurator().apply(motor1Config);
  }

  public void setTargetAngle(double angle) {
    motor1.setControl(control.withPosition(angle / 360).withSlot(0));
  }

  public double getCANcoderPos() {
    return angle.getValueAsDouble() * 360;
  }

  @Override
  public void periodic() {
    StatusSignal.refreshAll(angle);
  }

  private static Hood hood;
  public static Hood getInstance() {
    if (hood == null) {
      hood = new Hood();
    }
    return hood;
  }
}
