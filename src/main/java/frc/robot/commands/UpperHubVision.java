package frc.robot.commands;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PhotonVision;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class UpperHubVision extends CommandBase {

  private PhotonVision upperHubVision;

  //Init class
  public UpperHubVision(DriveTrain drive, Intake intake) {
    upperHubVision = new PhotonVision(drive, intake);
  }

  // //Command call init
  // @Override
  // public void initialize() {
  //   upperHubVision.moveToShootUpper();
  // }

  //Command call execute
  @Override
  public void execute() {
    System.out.println(upperHubVision.getDistanceToTarget());
  }

  // @Override
  // public void end(boolean interrupted) {

  // }
}