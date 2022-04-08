package frc.robot.commands.climb.groups;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ClimberConstants;
import frc.robot.commands.KillClimber;
import frc.robot.commands.climb.individual.*;
import frc.robot.subsystems.Climber;

    
public class TraversalClimb extends SequentialCommandGroup {
    public TraversalClimb(Climber climber) {
        addCommands(
            // 4)	Energize tilt motor backward at 25% tilt speed 
            // new RunCommand(
            // () -> climber.tiltRobot(ClimberConstants.kBackTiltSpeed), 
            // climber
            // ).withTimeout(1),
            //new StartEndCommand(
                //() -> climber.tiltRobot(ClimberConstants.kBackTiltSpeed), 
                //() -> climber.stopTilt(),  
                //climber
            //).withTimeout(1),
            new RaiseArm(climber).withTimeout(1.5),
            new RunCommand(
                () -> climber.tiltRobot(ClimberConstants.kTraversalTiltSpeed), 
                climber
            ).withTimeout(1),
            new WaitCommand(0.5),
            new RunCommand(
                () -> climber.retractArm(),
                climber
            ).withTimeout(1),
            new KillClimber(climber)
        );
    }

}
