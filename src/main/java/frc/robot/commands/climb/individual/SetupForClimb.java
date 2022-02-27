package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;

/**
 * A command to setup the robot for a bar to be climbed.
 */
public class SetupForClimb extends CommandBase {
  private final Climber m_climber;
  private final DriveTrain m_dDriveTrain;

  /**
   * Creates a new SetupForClimb
   * 
   * @param climber The climber subsystem this command will run on
   */
  public SetupForClimb(Climber climber, DriveTrain drive) {
    m_climber = climber;
    m_dDriveTrain = drive; 
  
    addRequirements(m_climber, m_dDriveTrain);
  }

  @Override
  public void initialize() {
    m_climber.extendArm();      
  }

  
  @Override
  public void execute() {

  }

  @Override
  public boolean isFinished() {
    return m_climber.ArmIsFullyExtended();
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    m_climber.stopWinch();
    if(!interrupted) {
      m_dDriveTrain.setMaxOutput(ClimberConstants.kMaxDriveSpeed);
    }
  }
}