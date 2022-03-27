package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TurnToBall extends CommandBase{

    private DriveTrain m_gyro;
    private DriveTrain m_drive;

    private boolean m_cancel = false;

    @Override
    public void initialize() {
        m_gyro.resetAngle();
    }

    @Override
    public void execute() {
        m_drive.arcadeDrive(0,0.2);
    }

    @Override
    public boolean isFinished() {
      return (m_gyro.isAtTargetAngle() || m_cancel);
    }

    @Override
    public void end(boolean m_cancel) {
        m_drive.arcadeDrive(0,0);
    }
}
