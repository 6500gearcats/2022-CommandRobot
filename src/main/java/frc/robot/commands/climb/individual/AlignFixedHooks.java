package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to align the fixed hooks of the robot for climbing.
 */
public class AlignFixedHooks extends CommandBase {
  /**
   * Creates a new AlignFixedHooks
   * 
   * @param climber The climber subsystem this command will run on
   */
  public AlignFixedHooks(Climber climber) {
    addRequirements(climber);
  }

  /**
   * Calling execute should result in the robot doing the following: 
   *    
   *    1. Tilting the robot's arm forwards at 10% tilt speed for 1 second
   *
   */
  @Override
  public void execute() {
  }
}