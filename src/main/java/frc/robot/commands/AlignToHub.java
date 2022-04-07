package frc.robot.commands;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.HubVision;
import frc.robot.subsystems.ModdedPIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AlignToHub extends CommandBase {

  //Define photonvision sub system
  private HubVision m_upperHubVision;
  
  //Define drive
  private final DriveTrain m_drive;

  private int iteration;

  //Init class
  public AlignToHub(DriveTrain drive, HubVision vision) {
    
    //Set drive and intake to parameters
    m_drive = drive;
    m_upperHubVision = vision;
    addRequirements(m_drive, m_upperHubVision);

    //Set drive max
    m_drive.setMaxOutput(DriveConstants.kMaxSpeed);

  }

  @Override
  public void initialize() {
    iteration = 0;
  }

  //Command call execute
  @Override
  public void execute() {

    //Define arcade speed
    ModdedPIDController.PIDReturnValues fowardsSpeed = m_upperHubVision.getFowardsPIDValues();
    ModdedPIDController.PIDReturnValues rotationSpeed = m_upperHubVision.getRotationPIDValues();

    System.out.println(
        iteration + 
        "," + 
        m_upperHubVision.getDistanceToTarget() + 
        "," + 
        -fowardsSpeed.output + 
        "," + 
        -fowardsSpeed.position + 
        "," + 
        -fowardsSpeed.integral + 
        "," + 
        -fowardsSpeed.derivative
    );

    HubVision.arcadeDriveSpeeds speeds = m_upperHubVision.new arcadeDriveSpeeds(-fowardsSpeed.output, -rotationSpeed.output, 0.7);

    m_drive.arcadeDrive(speeds.getFowardSpeed(), speeds.getRotationSpeed());

    iteration++;
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }
}