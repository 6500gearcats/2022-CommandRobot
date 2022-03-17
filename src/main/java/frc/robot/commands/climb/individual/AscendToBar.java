package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command used to ascend the robot to a hooked bar for climbing.
 */
public class AscendToBar extends CommandBase {
  /**
   * Creates a new AscendToBar
   * 
   * @param climber The climber subsystem this command will run on
   */
  public AscendToBar(Climber climber) {
    addRequirements(climber);
  }

  /**
   * Calling execute should result in the robot doing the following: 
   *    
   *    1. Run its winch in "retract" at 100% speed for 2 seconds after
   *       which the winch should be set to brake at zero speed
   * 
   *    2. Tilting its arm backwards at 50% speed until the rear tilt 
   *       switch closes after which the arm speed should be reduced to 5%
   *    
   *    3. Run its winch in "retract" at 100% speed until the bottom
   *       limit switch closes after which the winch speed should be reduced to 5%
   */
  @Override
  public void execute() {
  }
}