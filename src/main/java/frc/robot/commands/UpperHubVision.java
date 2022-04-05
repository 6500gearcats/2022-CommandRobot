package frc.robot.commands;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PhotonVision;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class UpperHubVision extends CommandBase {

  //Define photonvision sub system
  private PhotonVision upperHubVision;
  
  //Define drive
  private final DriveTrain m_drive;

  //Init class
  public UpperHubVision(DriveTrain drive) {
    
    //Set drive and intake to parameters
    m_drive = drive;
    addRequirements(m_drive);

    //Set drive max
    m_drive.setMaxOutput(DriveConstants.kMaxSpeed);

    upperHubVision = new PhotonVision();
  }

  //Command call execute
  @Override
  public void execute() {
    //Print variables
    System.out.println("Distance in inches: " + upperHubVision.getDistanceToTarget());
    System.out.println("Yaw: " + upperHubVision.getYaw());

    //Define speeds
    double ySpeed = upperHubVision.ySpeedToTarget();
    double xSpeed = upperHubVision.xSpeedToTarget();

    //Use x speed first and then y speed to get to target
    if(ySpeed != 0) {
      m_drive.arcadeDrive(xSpeed, 0);
    } else {
      m_drive.arcadeDrive(0, ySpeed);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }
}