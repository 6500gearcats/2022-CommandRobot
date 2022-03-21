package frc.robot.commands;


import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class VisionSteer extends CommandBase {
  private final DriveTrain m_drive;
  private final DoubleSupplier m_forward;
  private double m_maxSpeed;
  private double m_visionInput;


  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public VisionSteer(DriveTrain subsystem, DoubleSupplier forward) {
    m_drive = subsystem;
    m_forward = forward;
    m_maxSpeed = DriveConstants.kSlowSpeed;
    addRequirements(m_drive);
  }


  @Override
  public void initialize() {
    m_drive.setMaxOutput(m_maxSpeed);
  }

  @Override
  public void execute() {
    m_visionInput = SmartDashboard.getNumber("target offset", 0.0);
    
    Double forward = m_forward.getAsDouble();
    Double rotation = 0.0;
    if (Math.abs(m_visionInput) > 0.1 ) {
      rotation = m_visionInput/2;
    }
    
    m_drive.arcadeDrive(forward,rotation);
  }

  // @Override
  // public boolean isFinished() {
  //   return (Math.abs(m_visionInput) <= 0.1);
  // }

}