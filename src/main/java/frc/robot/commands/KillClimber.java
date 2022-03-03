package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class KillClimber extends CommandBase {
 
    private final Climber m_climberSystem;

    public KillClimber(Climber theClimber) {
        m_climberSystem = theClimber;
        addRequirements(m_climberSystem);
    }

    @Override
    public void initialize() {
        m_climberSystem.stopWinch();
       // m_climberSystem.stopTilt();
    }
  
    @Override
    public boolean isFinished() {
      return true;
    }


}
