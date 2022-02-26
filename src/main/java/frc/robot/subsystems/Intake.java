package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase{

    /**
   *
   */
    
    public final MotorController m_intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotorPort, MotorType.kBrushed);
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(IntakeConstants.i2cPort);

    private boolean m_bBallPresent = false;

    public Intake() {}

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }
    
    
    public void setReverse() {
      // We will assume that set the motor in reverse will spit a ball out (or not pick up a ball)
      m_intakeMotor.set(IntakeConstants.kIntakeReverseSpeed);
    }

    public void setPickupSpeed() {
      m_intakeMotor.set(IntakeConstants.kIntakePickupSpeed);
    }

    public void setPushBallSpeed() {
      m_intakeMotor.set(IntakeConstants.kPushBallSpeed);
    }

    public boolean ballIsPresent() {
      boolean ballIsPresent = false;
      int proxValue = m_colorSensor.getProximity();
      ballIsPresent = proxValue > IntakeConstants.kBallPresentThreshold;
      return ballIsPresent;
    }

    public void stop() {
      m_intakeMotor.stopMotor();
    }
   

}
