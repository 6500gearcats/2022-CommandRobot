package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

public class LiftBall extends CommandBase {
 
    public final Elevator m_elevatorSystem;
    public final Intake m_intakeSystem;

    public boolean m_cancel = false;
    
    public LiftBall(Elevator theElevator, Intake theIntake) {
        m_elevatorSystem = theElevator;
        m_intakeSystem = theIntake;
        addRequirements(m_elevatorSystem, m_intakeSystem);
        m_cancel = false;
    }

    @Override
    public void initialize() {
        m_cancel = false;
        if (m_elevatorSystem.isLoaded() || (!m_intakeSystem.ballIsPresent())) {
            m_cancel = true;
        }
        else {
            m_elevatorSystem.startMotor();        
        }
    }
  
    @Override
    public void execute() {
        if (m_intakeSystem.ballIsPresent()) {
            m_intakeSystem.setPushBallSpeed();        
        }
        else {
            m_intakeSystem.stop();
        }
    }
  
    @Override
    public boolean isFinished() {
      return (m_elevatorSystem.isBallAtTop() || m_cancel);
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_elevatorSystem.stop();
        m_intakeSystem.stop();
    }
}
