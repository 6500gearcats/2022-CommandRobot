package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to transfer the robot when climbing to the fixed hooks.
 */
public class TransferToFixedHooks extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new TransferToFixedHooks
   * 
   * @param climber The climber subsystem this command will run on.
   */
  public TransferToFixedHooks(Climber climber) {
    m_climber = climber; 

    addRequirements(climber);
  }

  @Override
  public void execute() {
  }
}