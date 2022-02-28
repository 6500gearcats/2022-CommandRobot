package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class PickupBall extends CommandBase {
 
    private final Intake m_intakeSystem;

    public PickupBall(Intake theIntake) {
        m_intakeSystem = theIntake;
        addRequirements(m_intakeSystem);
    }

    @Override
    public void initialize() {
        m_intakeSystem.setPickupSpeed();
    }
  
    @Override
    public boolean isFinished() {
      return m_intakeSystem.isBallPresent();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
    // NOTE: Doesn't stop in simulation due to lower friction causing the
    // can to fall out
    // + there is no need to worry about stalling the motor or crushing the
    // can.
        m_intakeSystem.stop();
    }
}
