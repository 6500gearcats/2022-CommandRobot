package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants.IntakeLifterConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class IntakeLifter extends SubsystemBase {

    public final MotorController m_intakeLifterMotor = new CANSparkMax(IntakeLifterConstants.kIntakeLifterMotorPort, MotorType.kBrushed);
    
    private boolean m_bIntakeLifted = false; 

    public IntakeLifter() {}

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

    public boolean isIntakeLifted() {
        double LiftSpeed = LifterMotorSpeed();
        m_bIntakeLifted = LiftSpeed < IntakeLifterConstants.kIntakeLiftedThreshhold;
        return m_bIntakeLifted;
      }
  

    private double LifterMotorSpeed() {
        return m_intakeLifterMotor.get();
    }

    public void stop() {
      m_intakeLifterMotor.stopMotor();
    }
    
}
