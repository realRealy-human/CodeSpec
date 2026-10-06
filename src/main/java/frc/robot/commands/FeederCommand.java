package frc.robot.commands;

import frc.robot.subsystems.Feeder.FeederState;

public class FeederCommand extends CommandTemplate<FeederState> {
    /** Creates a new CommandTemplate. 
     * <p>Use addRequirements() here to declare subsystem dependencies.</p> 
     * @param state The default state. (eg. IDLE) */
    public FeederCommand(FeederState state) {
        super(state);
    }
    
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        switch (state) {
            case FORWARD:

                break;
            case HOLD:
                break;
            case IDLE:
                break;
            case SHOOT:
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
}
