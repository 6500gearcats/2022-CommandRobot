package frc.robot.commands;

import org.photonvision.*;
import org.photonvision.targeting.PhotonTrackedTarget;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class UpperHubVision extends CommandBase {

  //Define PhotonVision camera
  PhotonCamera camera = new PhotonCamera("Camera1");

  //Define Drive variables
  private final DriveTrain m_drive;
  private final Intake m_intake;
  private double m_maxSpeed;

  //Init class
  public UpperHubVision(DriveTrain drive, Intake intake) {
    m_drive = drive;
    m_intake = intake;
    m_maxSpeed = DriveConstants.kMaxSpeed;
    addRequirements(m_drive);
  }

  //Command call init
  @Override
  public void initialize() {
    m_drive.setMaxOutput(m_maxSpeed);
  }

  //Command call execute
  @Override
  public void execute() {

    //Get latest results from camera
    var result = camera.getLatestResult();

    //If there are targets
    if(result.hasTargets()) {

      //Get best target
      PhotonTrackedTarget bestTarget = result.getBestTarget();

      //Print general info
      System.out.println(
        "Area: " + bestTarget.getArea() +
        " | Yaw: " + bestTarget.getYaw() +
        " | Pitch: " + bestTarget.getPitch() +
        " | Skew: " + bestTarget.getSkew()
      );

      //########################################################################
      //EXPERIMENTAL - Run at your own risk, idk what im doig atm
      //########################################################################

      //Look at upper hub
      m_drive.arcadeDrive(
        translate(bestTarget.getYaw(), -30, 30, -1, 1),
        0
      ); 

    // If there are no targets, print and stop drive
    } else {
      System.out.println("No targets found");
      m_drive.arcadeDrive(0, 0); 
    }

  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0); 
  }

  private double translate(double value, double oldMin, double oldMax, double newMin, double newMax) {
    //Figure out how wide each range is
    double oldSpan = oldMax - oldMin;
    double newSpan = newMax - newMin;

    //Convert the left range into a 0-1 range
    double valueScaled = (value - oldMin) / oldSpan;

    //Convert the 0-1 range into a value in the right range
    return newMin + (valueScaled * newSpan);
  }
}