package frc.robot.commands;

import frc.robot.subsystems.Shooter.Shooter;
import frc.robot.subsystems.Shooter.ShooterConstants;
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
            case IDLE:
                shooter.stopMotors();
                break;
            case SHOOT:
                shooter.setTargetVelocity(ShooterConstants.SHOOTING_VELOCITY);
                break;
            case EJECT:
                shooter.setTargetVelocity(ShooterConstants.EJECT_VELOCITY);
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
