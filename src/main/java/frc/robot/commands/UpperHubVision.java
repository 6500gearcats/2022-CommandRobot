package frc.robot.commands;
import org.photonvision.*;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class UpperHubVision extends CommandBase {

  PhotonCamera camera = new PhotonCamera("photonvision");

  public UpperHubVision() {
  }

  @Override
  public void initialize() {
    System.out.println("BUTTON PRESSED - INIT");
  }

  @Override
  public void execute() {
    var result = camera.getLatestResult();
    if(result.hasTargets()) {
      System.out.println(result.getBestTarget().toString());
    } else {
      System.out.println("No targets found");
    }
  }

  // @Override
  // public boolean isFinished() {
  // }

  @Override
  public void end(boolean interrupted) {
    System.out.println("BUTTON PRESSED - END");
  }
}