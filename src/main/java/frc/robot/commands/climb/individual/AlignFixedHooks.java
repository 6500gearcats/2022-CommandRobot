package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to align the fixed hooks of the robot for climbing.
 */
public class AlignFixedHooks extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new AlignFixedHooks
   * 
   * @param climber The climber subsystem this command will run on.
   */
  public AlignFixedHooks(Climber climber) {
    m_climber = climber; 

    addRequirements(climber);
  }

  @Override
  public void execute() {
  }
}