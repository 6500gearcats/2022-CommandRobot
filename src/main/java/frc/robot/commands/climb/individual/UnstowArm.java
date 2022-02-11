package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to unstow the robot's arm after reaching the hangar and prior to climbing
 */
public class UnstowArm extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new UnstowArm
   * 
   * @param climber The climber subsystem this command will run on
   */
  public UnstowArm(Climber climber) {
    m_climber = climber; 
  
    addRequirements(climber);
  }

  /**
   * Calling execute should result in the robot doing the following: 
   *    TODO: Add steps for unstowing the robot's arm
   */
  @Override
  public void execute() {
  }
}