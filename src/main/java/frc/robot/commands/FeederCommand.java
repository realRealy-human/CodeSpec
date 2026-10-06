package frc.robot.commands;

import java.util.function.BooleanSupplier;

import frc.robot.subsystems.Feeder.Feeder;
import frc.robot.subsystems.Feeder.FeederConstants;
import frc.robot.subsystems.Feeder.FeederState;

public class FeederCommand extends CommandTemplate<FeederState> {
    private Feeder feeder;

    protected BooleanSupplier canMove = () -> 
        !(isSubsystemState(FeederState.SHOOT) && !ShooterCommand.getInstance(null).isReady())
    ;

    /** Creates a new CommandTemplate. 
     * <p>Use addRequirements() here to declare subsystem dependencies.</p> 
     * @param state The default state. (eg. IDLE) */
    private FeederCommand(FeederState state) {
        super(state);
        feeder = Feeder.getInstance();
    }

    @Override
    public void initialize() {
    }

    @Override
    public void switchState() {
        switch (state) {
            case IDLE:
                feeder.setMotorVoltage(0);
                break;
            case HOLD:
                feeder.setMotorVoltage(0);
                break;
            case SHOOT:
                feeder.setMotorVoltage(FeederConstants.SHOOTING_VOLTAGE);
                break;
            case FORWARD:
                feeder.setMotorVoltage(FeederConstants.FORWARD_VOLTAGE);
                break;
        }
    }
    @Override
    protected void cannotMove() {
        feeder.setMotorVoltage(0);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private static FeederCommand instance;
    public static FeederCommand getInstance(FeederState state) {
        if (instance == null) {
            instance = new FeederCommand(state);
        }
        return instance;
    }
}
