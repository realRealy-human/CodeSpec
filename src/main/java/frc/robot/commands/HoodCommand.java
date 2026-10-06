package frc.robot.commands;

import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.Hood.HoodConstants;
import frc.robot.subsystems.Hood.HoodState;

public class HoodCommand extends CommandTemplate<HoodState> {
    private Hood hood;

    /** Creates a new CommandTemplate. 
     * <p>Use addRequirements() here to declare subsystem dependencies.</p> 
     * @param state The default state. (eg. IDLE) */
    private HoodCommand(HoodState state) {
        super(state);
        hood = Hood.getInstance();
    }
    
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        switch (state) {
            case IDLE:
                hood.setTargetAngle(0);
                break;
            case SHOOT:
                hood.setTargetAngle(HoodConstants.SHOOTING_ANGLE);
                break;
            case EJECT:
                hood.setTargetAngle(HoodConstants.EJECT_ANGLE);
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

    private static HoodCommand instance;
    public static HoodCommand getInstance(HoodState state) {
        if (instance == null) {
            instance = new HoodCommand(state);
        }
        return instance;
    }
}
