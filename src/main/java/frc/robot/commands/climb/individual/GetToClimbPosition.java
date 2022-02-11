package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to move the robot to the initial position to start climbing.
 */
public class GetToClimbPosition extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new GetToClimbPosition
   * 
   * @param climber The climber subsystem this command will run on.
   */
  public GetToClimbPosition(Climber climber) {
    m_climber = climber; 

    addRequirements(climber);
  }

  @Override
  public void execute() {
  }
}