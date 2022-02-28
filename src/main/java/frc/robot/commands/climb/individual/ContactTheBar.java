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
   * @param climber The climber subsystem this command will run on
   */
  public ContactTheBar(Climber climber) {
    m_climber = climber; 

    addRequirements(climber);
  }

  /**
   * Calling execute should result in the robot doing the following: 
   *    
   *    1. Tilting the robot's arm backwards at 10% tilt speed for 1 second
   *
   */
  @Override
  public void execute() {
  }
}