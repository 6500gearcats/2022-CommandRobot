package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;

public class ShootBall extends CommandBase {
   
    private final Shooter m_ShooterSystem;
    private final Elevator m_ElevatorSystem;

    public ShootBall(Shooter theShooter, Elevator theElevator){
        m_ShooterSystem = theShooter;
        m_ElevatorSystem = theElevator;
        addRequirements(m_ShooterSystem, m_ElevatorSystem);
    }


    @Override 
    public void initialize(){
        m_ShooterSystem.setShooterSpeed();
    }

    @Override
    public void execute(){
        if (m_ShooterSystem.shooterSpeedSet()){
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