package frc.robot.commands.climb.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.climb.individual.PrepArm;
import frc.robot.commands.climb.individual.RaiseArm;
import frc.robot.subsystems.Climber;

/**
 * Sequential command that encapsulates the various sub-commands used
 * by the robot in climbing a bar in the climbing challenge
 */
public class SetupForClimb extends SequentialCommandGroup {
    /**
     * Creates a new ClimbBar.
     * 
     * @param climber The climber subsystem this command will run on
     */
    public SetupForClimb(Climber climber) {
        addCommands(
            // Setup the robot for climbing the bar
            new PrepArm(climber).withTimeout(0.5),
            new RaiseArm(climber)
        );
    }

}
