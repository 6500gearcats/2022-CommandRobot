package frc.robot.commands;
import frc.robot.commands.climb.individual.ParkArm;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//TODO Make sure the code works.
//Seth is not responsible for any damage done by this code.

public class AutoCommand extends SequentialCommandGroup {

    public AutoCommand(DriveTrain DriveTrain, Shooter shooter, Elevator elevator, Climber climber) {
        addCommands(
            new StoreArm(climber).withTimeout(0.2),
            new ParkArm(climber),
            new ShootBallFast(shooter, elevator).withTimeout(2),
            new RunCommand(
                    () -> DriveTrain.arcadeDrive(0.7,0), 
                    DriveTrain
                ).withTimeout(1)

        );
    }
}