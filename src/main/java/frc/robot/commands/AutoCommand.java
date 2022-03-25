package frc.robot.commands;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class AutoCommand extends SequentialCommandGroup {
    public AutoCommand(DriveTrain DriveTrain, Shooter shooter, Elevator elevator) {
        addCommands(
            new ShootBallFast(shooter, elevator).withTimeout(2),
        new RunCommand(
                () -> DriveTrain.arcadeDrive(0.7,0), 
                DriveTrain
            ).withTimeout(1)


        );
    }
}