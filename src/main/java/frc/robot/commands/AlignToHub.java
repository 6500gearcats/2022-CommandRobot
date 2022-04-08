package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.HubVision;
import frc.robot.subsystems.LEDSetter;
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
    m_drive.arcadeDrive(speeds.getFowardSpeed(), speeds.getRotationSpeed());

    //Set the LED color based on based on distanceToTarget
    double postionError = m_upperHubVision.getDistanceToTarget() - Constants.VisionConstants.targetDistanceFromHub;
    if(Math.abs(postionError) < Constants.VisionConstants.marginForError) {
      LEDSetter.setEntireStripColor(Constants.LEDConstants.greenRGB);
    } else {
      LEDSetter.setPercentageOfStrip(Math.abs(postionError)/100, Constants.LEDConstants.blackRGB, Constants.LEDConstants.whiteRGB);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
    
    //Set LED strip
    LEDSetter.setEntireStripColor(Constants.LEDConstants.tealRGB);
  }
}