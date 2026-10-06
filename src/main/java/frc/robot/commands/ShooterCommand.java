package frc.robot.commands;

import java.util.function.BooleanSupplier;

import frc.robot.subsystems.Shooter.Shooter;
import frc.robot.subsystems.Shooter.ShooterConstants;
import frc.robot.subsystems.Shooter.ShooterState;

public class ShooterCommand extends CommandTemplate<ShooterState> {
    private Shooter shooter;

    protected BooleanSupplier canMove = () -> 
        !(isSubsystemState(ShooterState.SHOOT) && (!isInAllinceZone() || !isShiftActive()))
    ;

    private boolean isInAllinceZone() {
        return true; // coding this was not required for the project
    }
    private boolean isShiftActive() {
        return false; // same as isInAllinceZone()
    }

    /** Creates a new CommandTemplate. 
     * <p>Use addRequirements() here to declare subsystem dependencies.</p> 
     * @param state The default state. (eg. IDLE) */
    private ShooterCommand(ShooterState state) {
        super(state);
        shooter = Shooter.getInstance();
    }

    public boolean isReady() {
        return Math.abs(shooter.getVelocity() - ShooterConstants.SHOOTING_VELOCITY) < ShooterConstants.VELOCITY_TOLERANCE;
    }
    
    @Override
    public void initialize() {
    }

    @Override
    public void switchState() {
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
    protected void cannotMove() {
        shooter.stopMotors();
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
