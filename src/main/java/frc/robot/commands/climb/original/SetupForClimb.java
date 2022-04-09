package frc.robot.commands.climb.original;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.climb.individual.RaiseArm;
import frc.robot.subsystems.Climber;

/**
 * Sequential command that encapsulates the various sub-commands used
 * by the robot in climbing a bar in the climbing challenge
 */
public class SetupForClimb extends ParallelCommandGroup {
    /**
     * Creates a new ClimbBar.
     * 
     * @param climber The climber subsystem this command will run on
     */
    public SetupForClimb(Climber climber) {
        addCommands(
            // Setup the robot for climbing the bar
            new RaiseArm(climber)
        );
    }

}
