package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class VomitBall extends CommandBase {
 
    private final Intake m_intakeSystem;

    public VomitBall(Intake theIntake) {
        m_intakeSystem = theIntake;
        addRequirements(m_intakeSystem);
    }

    @Override
    public void initialize() {
        m_intakeSystem.setVomitSpeed();
    }
  
    @Override
    public boolean isFinished() {
      return m_intakeSystem.ballIsNotPresent();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_intakeSystem.stop();
    }
}
