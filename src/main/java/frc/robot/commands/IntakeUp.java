package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeLifter;

public class IntakeUp extends CommandBase {
 
    private final IntakeLifter m_intakeLifterSystem;

    public IntakeUp(IntakeLifter theIntakeLifter) {
        m_intakeLifterSystem = theIntakeLifter;
        addRequirements(m_intakeLifterSystem);
    }

    @Override
    public void initialize() {
        m_intakeLifterSystem.setPickupSpeed();
    }
  
    @Override
    public boolean isFinished(){
        return m_intakeLifterSystem.isIntakeLifted();
    }


    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_intakeLifterSystem.stop();
    }
}
