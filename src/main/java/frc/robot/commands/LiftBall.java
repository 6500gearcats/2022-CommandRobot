package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

public class LiftBall extends CommandBase {
 
    public final Elevator m_elevatorSystem;
    public final Intake m_intakeSystem;
    public static final double kPushBall = 0.5;

    public LiftBall(Elevator theElevator, Intake theIntake) {
        m_elevatorSystem = theElevator;
        addRequirements(m_elevatorSystem);
        m_intakeSystem = theIntake;
        addRequirements(m_intakeSystem);
    }

    @Override
    public void initialize() {
        m_elevatorSystem.startMotor();
        m_intakeSystem.pushBall();
    }
  
    @Override
    public boolean isFinished() {
      return m_elevatorSystem.isBallAtTop();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
    // NOTE: Doesn't stop in simulation due to lower friction causing the
    // can to fall out
    // + there is no need to worry about stalling the motor or crushing the
    // can.
        m_elevatorSystem.stop();
    }
}
