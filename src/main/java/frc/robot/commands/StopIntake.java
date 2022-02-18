package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class StopIntake extends CommandBase {
 
    private final Intake m_intakeSystem;

    public StopIntake(Intake theIntake) {
        m_intakeSystem = theIntake;
        addRequirements(m_intakeSystem);
    }

    @Override
    public void initialize() {
        m_intakeSystem.setReverse();
    }
  
    @Override
    public boolean isFinished() {
      return true;
    }


}
