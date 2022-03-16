package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Elevator;

public class AutoLift extends CommandBase{
    private final Intake m_intakeSystem;
    private final Elevator m_elevatorSystem;

    public AutoLift(Intake theIntake, Elevator theElevator){
        m_intakeSystem = theIntake;
        m_elevatorSystem = theElevator;
        addRequirements(m_intakeSystem, m_elevatorSystem);
    }

@Override
public void execute(){
    if(m_intakeSystem.ballRed()){
        m_intakeSystem.setVomitSpeed();
    }
    else if(m_intakeSystem.ballBlue()){
        m_elevatorSystem.startMotor(); 
        withTimeout(1);
    }
    else {
        m_intakeSystem.stop();
        m_elevatorSystem.stop();
    }
}

}
