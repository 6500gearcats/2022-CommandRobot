package frc.robot.commands.climb.groups;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Climber;

/**
 * Sequential command that encapsulates the various sub-commands used
 * by the robot in climbing a bar in the climbing challenge
 */
public class Climb3Bars extends SequentialCommandGroup {
    /**
     * Creates a new ClimbBar.
     * 
     * @param climber The climber subsystem this command will run on
     */
    public Climb3Bars(Climber climber) {
        addCommands(
            // Setup the robot for climbing the bar
            new Climb2Bars(climber),
            new TraversalClimb(climber)
        );
    }

}
