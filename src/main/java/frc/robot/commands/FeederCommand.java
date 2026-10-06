package frc.robot.commands;

import frc.robot.subsystems.Feeder.Feeder;
import frc.robot.subsystems.Feeder.FeederConstants;
import frc.robot.subsystems.Feeder.FeederState;

public class FeederCommand extends CommandTemplate<FeederState> {
    private Feeder feeder;

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
    public void execute() {
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
