package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase{
  public final MotorController m_elevatorMotor = new CANSparkMax(ElevatorConstants.kElevatorMotorPort, MotorType.kBrushed);

    public Elevator() {}

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }

    public void startMotor() {
      m_elevatorMotor.set(ElevatorConstants.kElevatorSpeed);
    }

    public boolean isBallAtTop() {
      return false;
    }

    public void stop() {
      m_elevatorMotor.stopMotor();
    }
    
}
