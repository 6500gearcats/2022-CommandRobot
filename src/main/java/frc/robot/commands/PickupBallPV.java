package frc.robot.commands;
import frc.robot.subsystems.BallVision;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;



public class PickupBallPV extends ParallelRaceGroup {
    public PickupBallPV(DriveTrain drive, Intake intake, BallVision vision) {
        addCommands(
            new PickupBall(intake),
            new FindBallPV(drive, vision)
        );
    }
}