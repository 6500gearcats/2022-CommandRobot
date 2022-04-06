package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ShooterPID;

public class ShootBalls extends SequentialCommandGroup {
   

    public ShootBalls(ShooterPID shooter, Elevator elevator, boolean goFast){

        if (goFast) {
            shooter.setSetpoint(ShooterConstants.kShooterFastTargetRPS);
        }
        else {
            shooter.setSetpoint(ShooterConstants.kShooterSlowTargetRPS);
        }
        addCommands(
            new InstantCommand(shooter::enable, shooter)
            .andThen(
                // Wait until the shooter is at speed before feeding the frisbees
                new WaitUntilCommand(shooter::atSetpoint),
                // Start running the feeder
                new InstantCommand(elevator::startMotor, elevator),
                // Shoot for the specified time
                new WaitCommand(ShooterConstants.kAutoShootTimeSeconds))
            // Add a timeout (will end the command if, for instance, the shooter never gets up to
            // speed)
            .withTimeout(ShooterConstants.kAutoTimeoutSeconds)
            // When the command ends, turn off the shooter and the feeder
            .andThen(
                () -> {
                    shooter.disable();
                    elevator.stop(); }
            )
        );            
    }
}