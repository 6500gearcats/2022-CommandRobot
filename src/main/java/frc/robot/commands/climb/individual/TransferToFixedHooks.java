package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to transfer the robot when climbing to the fixed hooks.
 */
public class TransferToFixedHooks extends CommandBase {
  /**
   * Creates a new TransferToFixedHooks
   * 
   * @param climber The climber subsystem this command will run on
   */
  public TransferToFixedHooks(Climber climber) {
    addRequirements(climber);
  }

  /**
   * Calling execute should result in the robot doing the following: 
   * 
   *    1. Run its winch in "extend" at 100% speed for 1 second after whic
   *       the winch should be set to brake at zero speed
   */
  @Override
  public void execute() {
  }
}