package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class LiftBall extends CommandBase {
 
    private final Elevator m_elevatorSystam;

    public LiftBall(Elevator theElevator) {
        m_elevatorSystam = theElevator;
        addRequirements(m_elevatorSystam);
    }

    @Override
    public void initialize() {
        m_elevatorSystam.startMotor();
    }
  
    @Override
    public boolean isFinished() {
      return m_elevatorSystam.isBallAtTop();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
    // NOTE: Doesn't stop in simulation due to lower friction causing the
    // can to fall out
    // + there is no need to worry about stalling the motor or crushing the
    // can.
        m_elevatorSystam.stop();
    }
}
