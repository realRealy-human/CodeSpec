package frc.robot.commands;

import frc.robot.subsystems.Shooter.Shooter;
import frc.robot.subsystems.Shooter.ShooterState;

public class ShooterCommand extends CommandTemplate<ShooterState> {
    private Shooter shooter;

    /** Creates a new CommandTemplate. 
     * <p>Use addRequirements() here to declare subsystem dependencies.</p> 
     * @param state The default state. (eg. IDLE) */
    private ShooterCommand(ShooterState state) {
        super(state);
        shooter = Shooter.getInstance();
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

    private static ShooterCommand instance;
    public static ShooterCommand getInstance(ShooterState state) {
        if (instance == null) {
            instance = new ShooterCommand(state);
        }
        return instance;
    }
}
