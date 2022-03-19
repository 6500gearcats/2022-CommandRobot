package frc.robot.commands.climb.groups;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.climb.individual.RaiseArm;
import frc.robot.subsystems.Climber;

/**
 * Sequential command that encapsulates the various sub-commands used
 * by the robot in climbing a bar in the climbing challenge
 */
public class Climb2Bars extends SequentialCommandGroup {
    /**
     * Creates a new ClimbBar.
     * 
     * @param climber The climber subsystem this command will run on
     */
    public Climb2Bars(Climber climber) {
        addCommands(
            // Setup the robot for climbing the bar
            new ClimbBar(climber),
            //new WaitCommand(1),
            new RaiseArm(climber),
           // new WaitCommand(1),          
            new ClimbBar(climber)
            
        );
    }

}
