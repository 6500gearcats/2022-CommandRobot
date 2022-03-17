package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;

/**
 * A command to move the robot to the initial position to start climbing.
 */
public class GetToClimbPosition extends CommandBase {
  /**
   * Creates a new GetToClimbPosition
   * 
   * @param climber The climber subsystem this command will run on
   */
  public GetToClimbPosition(Climber climber, DriveTrain drive) {
    addRequirements(
      climber,
      drive
    );
  }

  /**
   * Calling execute should result in the robot doing the following: 
   * 
   *    1. Moving to the hanger with the climbing bars
   * 
   *    2. Moving under the middle bar, and aligning itself with the bar
   * 
   *    3. Orienting itself to face towards the alliance wall
   */
  @Override
  public void execute() {
  }
}