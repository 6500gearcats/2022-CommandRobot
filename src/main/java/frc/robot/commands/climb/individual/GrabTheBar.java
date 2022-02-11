package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to make the robot grab a bar for climbing.
 */
public class GrabTheBar extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new GrabTheBar
   * 
   * @param climber The climber subsystem this command will run on.
   */
  public GrabTheBar(Climber climber) {
    m_climber = climber; 

    addRequirements(climber);
  }

  @Override
  public void execute() {
  }
}