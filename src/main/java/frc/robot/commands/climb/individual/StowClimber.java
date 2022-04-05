package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * A command to setup the robot for a bar to be climbed.
 */
public class StowClimber extends CommandBase {
  private final Climber m_climber;

  /**
   * Creates a new SetupForClimb
   * 
   * @param climber The climber subsystem this command will run on
   */
  public StowClimber(Climber climber) {
    m_climber = climber;
  
    addRequirements(m_climber);
  }

  @Override
  public void initialize() {
    m_climber.retractArm();
    m_climber.tiltRobot(0.25);
  }

  
  @Override
  public void execute() {

  }

  @Override
  public boolean isFinished() {
    return m_climber.ArmIsFullyRetracted();
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    m_climber.stopWinch();
    m_climber.stopTilt();
  }
}