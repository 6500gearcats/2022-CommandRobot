package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase{
  public final MotorController m_elevatorMotor = new CANSparkMax(ElevatorConstants.kElevatorMotorPort, MotorType.kBrushed);
  public final DigitalInput m_topSwitch = new DigitalInput(ElevatorConstants.kTopSwitchChannel);

    public Elevator() {}

    @Override
    public void periodic() {
      SmartDashboard.putBoolean("Elevator top switch", m_topSwitch.get());
      SmartDashboard.putNumber("Elevator motor", m_elevatorMotor.get());
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
      SmartDashboard.putBoolean("Elevator top switch", m_topSwitch.get());
      SmartDashboard.putNumber("Elevator motor", m_elevatorMotor.get());
    }

    public void startMotor() {
      m_elevatorMotor.set(ElevatorConstants.kElevatorSpeed);
    }

    public boolean isBallAtTop() {
      return m_topSwitch.get();

    }

    public void stop() {
      m_elevatorMotor.stopMotor();
    }

    public boolean isLoaded() {
        return isBallAtTop();
    }
    
}
