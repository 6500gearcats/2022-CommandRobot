package frc.robot.commands;


import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

/**
 * Parallel command that steers teh roboto to a target ball and is completed when a balls
 * is successfully picked up
 */
public class AutoPickup extends ParallelRaceGroup {
    /**
     * 
     */
    public AutoPickup(Intake intake, DriveTrain drive) {
        addCommands(
            // Start the Intake and
            new PickupBall(intake),
            new VisionSteer(drive)
        );
    }

}
