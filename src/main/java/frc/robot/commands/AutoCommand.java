package frc.robot.commands;
import frc.robot.commands.climb.individual.ParkArm;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoCommand extends SequentialCommandGroup {
    public AutoCommand(DriveTrain DriveTrain, Shooter shooter, Elevator elevator,  Climber climber, Intake intake) {
        addCommands(
            new StoreArm(climber).withTimeout(0.2),
            new ParkArm(climber),
            new ShootBallFast(shooter, elevator).withTimeout(2),
            new ParallelCommandGroup(
                new PickupBall(intake),
                new RunCommand(
                    () -> DriveTrain.arcadeDrive(-0.5,0), 
                    DriveTrain
                ).withTimeout(1.5)
            ),
            new LiftBall(elevator, intake),
            new RunCommand(
                () -> DriveTrain.arcadeDrive(0.5,0), 
                DriveTrain
            ).withTimeout(1.5),
            new RunCommand(
                    () -> DriveTrain.arcadeDrive(0,0), 
                    DriveTrain
                ).withTimeout(0.5),
            new ShootBallFast(shooter, elevator).withTimeout(2),
            new RunCommand(
                    () -> DriveTrain.arcadeDrive(-0.5,0), 
                    DriveTrain
                ).withTimeout(1.5),
            new TurnToAngle(90.0, DriveTrain).withTimeout(2)

        );
    }
}