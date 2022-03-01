package frc.robot.commands;


import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DefaultDrive extends CommandBase {
  private final DriveTrain m_drive;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;
  private double m_maxSpeed;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public DefaultDrive(DriveTrain subsystem, DoubleSupplier forward, DoubleSupplier rotation) {
    m_drive = subsystem;
    m_forward = forward;
    m_rotation = rotation;
    m_maxSpeed = DriveConstants.kMaxSpeed;
    addRequirements(m_drive);
  }

  public DefaultDrive(DriveTrain subsystem, DoubleSupplier forward, DoubleSupplier rotation, double speed) {
    m_drive = subsystem;
    m_forward = forward;
    m_rotation = rotation;
    addRequirements(m_drive);
    m_maxSpeed = speed;
  }

  @Override
  public void initialize() {
    m_drive.setMaxOutput(m_maxSpeed);
  }

  @Override
  public void execute() {
    Double forward = m_forward.getAsDouble();
    Double rotation = m_rotation.getAsDouble();
    m_drive.arcadeDrive(forward, rotation);
  }
}