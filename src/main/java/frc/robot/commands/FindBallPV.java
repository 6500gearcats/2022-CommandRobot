package frc.robot.commands;


import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.BallVision;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A command to find a ball using vision assistance}.
 */
public class FindBallPV extends CommandBase {
  private final DriveTrain m_drive;
  private final BallVision m_vision;
  private final NetworkTableInstance m_ntables;
  private boolean m_isRedAlliance;
  
  
  /**
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public FindBallPV(DriveTrain drive, BallVision vision) {
    m_drive = drive;
    m_vision = vision;
    addRequirements(m_drive, m_vision);
    
      // private int  m_visionFilterSamples;
    m_ntables = NetworkTableInstance.getDefault();

  }


  @Override
  public void initialize() {
    NetworkTable table = m_ntables.getTable("FMSInfo");
    m_isRedAlliance = table.getEntry("IsRedAlliance").getBoolean(true);
    if (m_isRedAlliance) {
        m_vision.setPipeline(VisionConstants.kRedBallPipelineIdx);
    }
    else {
        m_vision.setPipeline(VisionConstants.kBlueBallPipelineIdx);
    }
  }

  @Override
  public void execute() {
    double speed = 0.0;
    double rotation = 0.0;

    speed = m_vision.getForwardSpeed();
    rotation = m_vision.getRotation();

    m_drive.arcadeDrive(speed, rotation);
  }


  @Override
  public boolean isFinished() {
    return m_vision.hasTargets();
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

}