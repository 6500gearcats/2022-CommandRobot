package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.Constants.DriveConstants;
public class Shooter extends SubsystemBase{
  private final MotorController m_shooterMotor=
  new CANSparkMax(DriveConstants.m_shooterMotor1Port, MotorType.kBrushless);

    public Shooter() {
      
    }
  

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }
    
}
