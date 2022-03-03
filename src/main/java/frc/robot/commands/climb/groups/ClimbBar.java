package frc.robot.commands.climb.groups;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.climb.individual.AlignFixedHooks;
import frc.robot.commands.climb.individual.AscendToBar;
import frc.robot.commands.climb.individual.ContactTheBar;
import frc.robot.commands.climb.individual.RaiseArm;
import frc.robot.commands.climb.individual.TransferToFixedHooks;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;

/**
 * Sequential command that encapsulates the various sub-commands used
 * by the robot in climbing a bar in the climbing challenge
 */
public class ClimbBar extends SequentialCommandGroup {
    /**
     * Creates a new ClimbBar.
     * 
     * @param climber The climber subsystem this command will run on
     */
    public ClimbBar(Climber climber, DriveTrain drive) {
        addCommands(
            // Contact the robot's hooks with the bar being climbed
            new ContactTheBar(climber),

            // Ascend to the bar being climbed
            new AscendToBar(climber),

            // Align the robot's fixed hooks to the bar being climbed
            new AlignFixedHooks(climber),

            // Transfer to the robot's fixed hooks to finish the climb
            new TransferToFixedHooks(climber)
        );
    }

}
