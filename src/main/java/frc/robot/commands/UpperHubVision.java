package frc.robot.commands;
import org.photonvision.*;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class UpperHubVision extends CommandBase {

  PhotonCamera camera = new PhotonCamera("Camera1");

  // public UpperHubVision() {
  // }

  // @Override
  // public void initialize() {
  //   System.out.println("BUTTON PRESSED - INIT");
  // }

  @Override
  public void execute() {

    //Get latest results from camera
    var result = camera.getLatestResult();

    //If there are targets define best target and run vision code
    if(result.hasTargets()) {

      PhotonTrackedTarget bestTarget = result.getBestTarget();

      System.out.println(result.getBestTarget().toString());
      System.out.println(
        "Area: " + bestTarget.getArea() +
        " | Yaw: " + bestTarget.getYaw() +
        " | Pitch: " + bestTarget.getPitch() +
        " | Skew: " + bestTarget.getSkew()
      );

    } else {

      System.out.println("No targets found");

    }
  }

  // @Override
  // public boolean isFinished() {
  // }

  // @Override
  // public void end(boolean interrupted) {
  //   System.out.println("BUTTON PRESSED - END");
  // }
}