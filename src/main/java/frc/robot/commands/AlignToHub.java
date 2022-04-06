package frc.robot.commands;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.HubVision;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AlignToHub extends CommandBase {

  //Define photonvision sub system
  private HubVision m_upperHubVision;
  
  //Define drive
  private final DriveTrain m_drive;

  //Init class
  public AlignToHub(DriveTrain drive, HubVision vision) {
    
    //Set drive and intake to parameters
    m_drive = drive;
    m_upperHubVision = vision;
    addRequirements(m_drive, m_upperHubVision);

    //Set drive max
    m_drive.setMaxOutput(DriveConstants.kMaxSpeed);

  }

  //Command call execute
  @Override
  public void execute() {
    // //Print variables
    // System.out.println("Distance in inches: " + upperHubVision.getDistanceToTarget());
    // System.out.println("Yaw: " + upperHubVision.getYaw());

    //Define arcade speed
    HubVision.arcadeDriveSpeeds speeds = m_upperHubVision.getArcadeSpeed();
    System.out.println(speeds);
    System.out.println(m_upperHubVision.pidValuesAsString());
    m_drive.arcadeDrive(speeds.getFowardSpeed(), speeds.getRotationSpeed());
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }
}