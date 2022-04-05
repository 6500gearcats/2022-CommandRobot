package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants.IntakeLifterConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class IntakeLifter extends SubsystemBase {

    
    private final MotorController m_intakeLifterMotor;

    private final RelativeEncoder m_lifterEncoder;
    
    private boolean m_bIntakeLifted = false; 

    public IntakeLifter() {
      CANSparkMax lifterMotor =  new CANSparkMax(IntakeLifterConstants.kIntakeLifterMotorPort, MotorType.kBrushed);
      m_intakeLifterMotor = lifterMotor;
      m_lifterEncoder = lifterMotor.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, IntakeLifterConstants.kEncoderRate);

      lifterMotor.setSmartCurrentLimit(IntakeLifterConstants.kMaxLifterCurrent);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }
    
    
    public void setReverse() {
      m_intakeLifterMotor.set(IntakeLifterConstants.kIntakeLifterDownSpeed);
    }

    public void setPickupSpeed() {
      m_intakeLifterMotor.set(IntakeLifterConstants.kIntakeLifterUpSpeed);
    }
 
    public void stop() {
      m_intakeLifterMotor.stopMotor();
    }

    public void resetEncoder() {
      m_lifterEncoder.setPosition(0);
    }

    public double getRotations() {
      return Math.abs(m_lifterEncoder.getPosition());
    }
    
}
