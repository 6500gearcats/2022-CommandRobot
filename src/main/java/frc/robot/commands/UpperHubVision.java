package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PhotonVision;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class UpperHubVision extends CommandBase {

  //Define photonvision sub system
  private PhotonVision upperHubVision;
  
  //Define drive
  private final DriveTrain m_drive;
  private final Intake m_intake;

  //Init class
  public UpperHubVision(DriveTrain drive, Intake intake) {
    
    //Set drive and intake to parameters
    m_drive = drive;
    m_intake = intake;

    //Set drive max
    m_drive.setMaxOutput(DriveConstants.kMaxSpeed);

    upperHubVision = new PhotonVision();
  }

  // //Command call init
  // @Override
  // public void initialize() {
  //   upperHubVision.moveToShootUpper();
  // }

  //Command call execute
  @Override
  public void execute() {
    System.out.println("Distance in inches: " + upperHubVision.getDistanceToTarget());
    if(upperHubVision.getDistanceToTarget() > Constants.VisionConstants.targetDistanceFromHub) {
      m_drive.arcadeDrive(0.3, 0);
      System.out.println("Moving");
    } else {
      m_drive.arcadeDrive(0, 0);
      System.out.println("Not Moving");
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }
}