package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;

/**
 * A command to setup the robot for a bar to be climbed.
 */
public class RetractArm extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new SetupForClimb
   * 
   * @param climber The climber subsystem this command will run on
   */
  public RetractArm(Climber climber) {
    m_climber = climber;
  
    addRequirements(m_climber);
  }

  @Override
  public void initialize() {
    m_climber.retractArm();      
  }

  
  @Override
  public void execute() {

  }

  @Override
  public boolean isFinished() {
    boolean isRetracted = m_climber.ArmIsFullyRetracted();
    boolean isStalled = m_climber.winchIsStalled(); 
    return (isRetracted || isStalled);
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    m_climber.stopWinch();
  }
}