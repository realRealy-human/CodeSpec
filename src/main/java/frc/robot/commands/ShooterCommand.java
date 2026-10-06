package frc.robot.commands;

import frc.robot.subsystems.Shooter.ShooterState;

public class ShooterCommand extends CommandTemplate<ShooterState> {
    /** Creates a new CommandTemplate. 
     * <p>Use addRequirements() here to declare subsystem dependencies.</p> 
     * @param state The default state. (eg. IDLE) */
    public ShooterCommand(ShooterState state) {
        super(state);
    }
    
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        switch (state) {
            case EJECT:
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
