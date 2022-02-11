package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to contact the robot's hooks to bars used for climbing.
 */
public class ContactTheBar extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new ContactTheBar
   * 
   * @param climber The climber subsystem this command will run on.
   */
  public ContactTheBar(Climber climber) {
    m_climber = climber; 

    addRequirements(climber);
  }

  @Override
  public void execute() {
  }
}