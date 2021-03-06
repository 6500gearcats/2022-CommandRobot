package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;

public class ShootBallSlow extends CommandBase {
   
    private final Shooter m_ShooterSystem;
    private final Elevator m_ElevatorSystem;

    public ShootBallSlow(Shooter theShooter, Elevator theElevator){
        m_ShooterSystem = theShooter;
        m_ElevatorSystem = theElevator;
        addRequirements(m_ShooterSystem, m_ElevatorSystem);
    }


    @Override 
    public void initialize(){
        m_ShooterSystem.setShooterSpeedSlow();
    }

    @Override
    public void execute(){
        if (m_ShooterSystem.shooterSpeedSetSlow()){
            m_ElevatorSystem.startMotor();
        }

    }

    @Override
    public boolean isFinished(){
        return m_ShooterSystem.isBallFired();
    }

    @Override
    public void end(boolean interrupted) {
        m_ShooterSystem.stopShooter();
        m_ElevatorSystem.stop();
    }
}