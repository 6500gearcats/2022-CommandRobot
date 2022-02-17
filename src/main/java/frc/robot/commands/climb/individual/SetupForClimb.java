package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to setup the robot for a bar to be climbed.
 */
public class SetupForClimb extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new SetupForClimb
   * 
   * @param climber The climber subsystem this command will run on
   */
  public SetupForClimb(Climber climber) {
    m_climber = climber; 
  
    addRequirements(climber);
  }

  /**
   * Calling execute should result in the robot doing the following: 
   *    
   *    1. Tilting its arm forward at 50% tilt speed until the forward tilt switch 
   *       closes after which the tilt should be set to brake at zero speed
   * 
   *    2. Run its winch in "extend" at 100% speed until the top limit switch closes 
   *       after which the winch should be set to brake at zero speed
   */
  @Override
  public void execute() {
  }
}