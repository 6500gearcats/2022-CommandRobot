package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase{


  public final MotorController m_ShooterMotor = new CANSparkMax(ShooterConstants.kShooterMotorPort, MotorType.kBrushless);
    public Shooter() {}

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }

    public void setShooterSpeed(){
      m_ShooterMotor.set(ShooterConstants.kShooterSpeed);
    }
    
    public void stopShooter() {
      m_ShooterMotor.stopMotor();
    }
}
