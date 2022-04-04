package frc.robot.commands;


// import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.filter.MedianFilter;
// import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
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
  // private final DoubleSupplier m_forward;
  private double m_maxSpeed;
  private double m_visionInput;
  // private int  m_visionFilterSamples;
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("FMSInfo");
  // private boolean m_isRedAlliance;
  

  ShuffleboardTab tab = Shuffleboard.getTab("Drive");
    private NetworkTableEntry m_visionFilterSample =
    tab.add("Filter samples", 10)
      .getEntry();

  
  
  // Creates a MedianFilter with a window size of 5 samples
  private MedianFilter filter; 


  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public VisionSteer(DriveTrain subsystem, DoubleSupplier forward) {
    m_drive = subsystem;
    // m_forward = forward;
    m_maxSpeed = DriveConstants.kMaxSpeed;
    addRequirements(m_drive);

    double dSamples = m_visionFilterSample.getDouble(10.0);
    int samples = (int)dSamples;

    System.out.println("Setting vision sampler to: " + samples);

    filter = new MedianFilter(samples);

    // m_isRedAlliance = table.getEntry("IsRedAlliance").getBoolean(true);
    
  }


  @Override
  public void initialize() {
    m_drive.setMaxOutput(m_maxSpeed);

    
  }

  @Override
  public void execute() {
    Double forward = DriveConstants.kAutoSpeed;
    Double rotation = 0.0;
    m_visionInput = SmartDashboard.getNumber("target offset", 99);
    
    // Out of bounds value means we should stop
    if (Math.abs(m_visionInput) > 1) {
      m_drive.arcadeDrive(0, 0);  
    }
    else {
      // scale and INVERT for 2022 robot (upside-down pixy2)
      rotation = filter.calculate(m_visionInput) * -0.7;
      m_drive.arcadeDrive(forward,rotation);
    }
  }

  // @Override
  // public boolean isFinished() {
  //   return (Math.abs(m_visionInput) <= 0.1);
  // }

  @Override
  public void end(boolean interrupted) {
    m_visionInput = 0.0;
    filter.reset();
    m_drive.arcadeDrive(0, 0);
  }

}