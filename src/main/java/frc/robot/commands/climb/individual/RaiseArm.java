package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to setup the robot for a bar to be climbed.
 */
public class RaiseArm extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new SetupForClimb
   * 
   * @param climber The climber subsystem this command will run on
   */
  public RaiseArm(Climber climber) {
    m_climber = climber;
  
    addRequirements(m_climber);
  }

  @Override
  public void initialize() {
    m_climber.extendArm();      
  }

  @Override
  public boolean isFinished() {
    return m_climber.ArmIsFullyExtended();
  }


  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    m_climber.stopWinch();


  }
}