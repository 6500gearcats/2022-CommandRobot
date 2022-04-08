package frc.robot.commands;
import frc.robot.commands.climb.individual.ParkArm;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//TODO Make sure the code works.
//Seth is not responsible for any damage done by this code.

public class AutoCommandSimple extends SequentialCommandGroup {

    public AutoCommandSimple(DriveTrain DriveTrain, Shooter shooter, Elevator elevator, Climber climber, Intake intake) {
        addCommands(
            new StoreArm(climber).withTimeout(0.2),
            new ParkArm(climber),
            new ShootBallFast(shooter, elevator, intake).withTimeout(2),
            new RunCommand(
                    () -> DriveTrain.arcadeDrive(0.7,0), 
                    DriveTrain
                ).withTimeout(1)

        );
    }
}