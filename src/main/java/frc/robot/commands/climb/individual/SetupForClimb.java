package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to setup the setup the robot for the first bar to be climbed.
 * The expectation is for this to be run after getting to the climb position.
 */
public class SetupForClimb extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new SetupForClimb
   * 
   * @param climber The climber subsystem this command will run on.
   */
  public SetupForClimb(Climber climber) {
    m_climber = climber; 

    addRequirements(climber);
  }

  @Override
  public void execute() {
  }
}