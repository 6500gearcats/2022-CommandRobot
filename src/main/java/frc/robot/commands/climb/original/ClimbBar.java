package frc.robot.commands.climb.original;


import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Constants.ClimberConstants;
import frc.robot.commands.KillClimber;
import frc.robot.commands.climb.individual.*;
import frc.robot.subsystems.Climber;

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
    public ClimbBar(Climber climber) {
        addCommands(
            // 4)	Energize tilt motor backward at 25% tilt speed 
            new RunCommand(
                () -> climber.tiltRobot(ClimberConstants.kBackTiltSpeed), 
                climber
            ).withTimeout(1),

            // 5)	Wait 0.5 seconds for full bar engagement
            //new WaitCommand(0.5), 

            // Ascend to the bar being climbed
            // 6)	Energize winch in "retract" at 100% speed for 2s, then set to zero speed
            new StartEndCommand(
                () -> climber.retractArm(0.8), 
                () -> climber.stopWinch(),  
                climber
            ).withTimeout(2),

            // 7)	Wait 1s for robot to finish tilting
            //new WaitCommand(1), 

            //8)	Run winch in "retract" at 100% speed until bottom limit switch closes, then set to zero speed
            new RetractArm(climber),

            //9)	Switch tilt motor to forward at 25% speed
            new RunCommand(
                () -> climber.tiltRobot(0.4), 
                climber
            ).withTimeout(3),

            // 11)	Run winch in “extend” at 100% speed for 1s, then set to zero speed
            new StartEndCommand(
                () -> climber.extendArm(), 
                () -> climber.stopWinch(),  
                climber
            ).withTimeout(1)

            

        );
    }

}
