package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;

@SuppressWarnings("rawtypes")
public abstract class CommandTemplate<State extends Enum> extends Command {
  protected State state;

  protected BooleanSupplier canMove;

  /** Creates a new CommandTemplate. 
   * <p>Use addRequirements() here to declare subsystem dependencies.</p> 
   * @param state The default state. (eg. IDLE) */
  protected CommandTemplate(State state) {
    this.state = state;
  }

  public State getState() {
    return state;
  }
  public void setState(State state) {
    this.state = state;
  }

  protected boolean isSubsystemState(State state) {
    return this.state.equals(state);
  }

  @Override
  public void execute() {
    if (!canMove.getAsBoolean()) {
      cannotMove();
    } else {
      switchState();
    }
  }
  protected abstract void cannotMove();

  protected abstract void switchState();

  // @Override
  // public void initialize() {}
  // 
  // 
  // @Override
  // public void end(boolean interrupted) {}
  // 
  // @Override
  // public boolean isFinished() {
  //   return false;
  // }
}
