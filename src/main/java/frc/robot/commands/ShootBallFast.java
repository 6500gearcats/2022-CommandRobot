package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

public class ShootBallFast extends CommandBase {
   
    private final Shooter m_ShooterSystem;
    private final Elevator m_ElevatorSystem;
    private final Intake m_IntakeSystem;

    public ShootBallFast(Shooter theShooter, Elevator theElevator, Intake theIntake){
        m_ShooterSystem = theShooter;
        m_ElevatorSystem = theElevator;
        m_IntakeSystem = theIntake;
        addRequirements(m_ShooterSystem, m_ElevatorSystem);
    }


    @Override 
    public void initialize(){
        m_ShooterSystem.setShooterSpeedFast();
    }

    @Override
    public void execute(){
        if (m_ShooterSystem.shooterSpeedSetFast()){
            m_ElevatorSystem.startMotor();
            m_IntakeSystem.setPickupSpeed();
        }
        
    }


    @Override
    public void end(boolean interrupted) {
        m_ShooterSystem.stopShooter();
        m_ElevatorSystem.stop();
    }
}