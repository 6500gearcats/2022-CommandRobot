package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command used to ascend the robot to a hooked bar for climbing.
 */
public class AscendToBar extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new AscendToBar
   * 
   * @param climber The climber subsystem this command will run on.
   */
  public AscendToBar(Climber climber) {
    m_climber = climber; 

    addRequirements(climber);
  }

  @Override
  public void execute() {
  }
}