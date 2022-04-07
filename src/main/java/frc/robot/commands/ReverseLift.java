package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ReverseLift extends CommandBase {
 
    public final Elevator m_elevatorSystem;
    public final Intake m_intakeSystem;
    public final Shooter m_shooterSystem;

    public boolean m_cancel = false;
    
    public ReverseLift(Elevator theElevator, Intake theIntake, Subsystem theShooter) {
        m_elevatorSystem = theElevator;
        m_intakeSystem = theIntake;
        m_shooterSystem = (Shooter)theShooter;
        addRequirements(m_elevatorSystem, m_intakeSystem, m_shooterSystem);
        m_cancel = false;
    }

    @Override
    public void initialize() {
            m_elevatorSystem.reverseMotor();
            //m_shooterSystem.reverseMotor();        
        
    }
  
    @Override
    public void execute() {
        m_elevatorSystem.reverseMotor();
        //m_shooterSystem.reverseMotor();
    }
  
    @Override
    public boolean isFinished() {
      return (false);
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_elevatorSystem.stop();
        m_shooterSystem.disable();
    }
}
