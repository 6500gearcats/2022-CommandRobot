package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

public class ReverseLift extends CommandBase {
 
    public final Elevator m_elevatorSystem;
    public final Intake m_intakeSystem;

    public boolean m_cancel = false;
    
    public ReverseLift(Elevator theElevator, Intake theIntake) {
        m_elevatorSystem = theElevator;
        m_intakeSystem = theIntake;
        addRequirements(m_elevatorSystem, m_intakeSystem);
        m_cancel = false;
    }

    @Override
    public void initialize() {
            m_elevatorSystem.reverseMotor();        
        
    }
  
    @Override
    public void execute() {
        m_elevatorSystem.reverseMotor();
    }
  
    @Override
    public boolean isFinished() {
      return (false);
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_elevatorSystem.stop();
    }
}
