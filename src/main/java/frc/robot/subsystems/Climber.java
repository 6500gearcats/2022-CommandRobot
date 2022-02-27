package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ClimberConstants;
import frc.robot.utility.EncoderOdometer;

public class Climber extends SubsystemBase{
  public final CANSparkMax m_winchMotor = new CANSparkMax(ClimberConstants.kWinchMotorPort, MotorType.kBrushless);
  public final CANSparkMax m_tiltMotor = new CANSparkMax(ClimberConstants.kTiltMotorPort, MotorType.kBrushless);


  //private RelativeEncoder m_winchEncoder;
  private RelativeEncoder m_winchEncoder;
  private RelativeEncoder m_tiltEncoder;
  private EncoderOdometer m_winchOdometer;

  private SparkMaxLimitSwitch m_forwardLimit;
  private SparkMaxLimitSwitch m_reverseLimit;

    public Climber() {
      m_forwardLimit = m_winchMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
      m_reverseLimit = m_winchMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);

      m_winchEncoder = m_winchMotor.getEncoder();
      m_tiltEncoder = m_tiltMotor.getEncoder();

      m_winchMotor.setIdleMode(IdleMode.kBrake);
      m_tiltMotor.setIdleMode(IdleMode.kBrake);

      m_winchOdometer = new EncoderOdometer(m_winchEncoder);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      SmartDashboard.putNumber("Arm position", m_winchOdometer.getPosition());
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }

    public void tiltArmForward(double speed) {
      m_tiltMotor.set(speed);
    }

    public boolean frontTiltSwitchClosed() {
      return false;
    }

    public void extendArm() {
      this.extendArm(ClimberConstants.kMaxWinchSpeed);
    }

    public void extendArm(double speed) {
      m_winchMotor.set(speed);
    }

    public boolean ArmIsFullyExtended() {
        return (m_forwardLimit.isPressed() 
            || (m_winchOdometer.getPosition() > ClimberConstants.kMaxWinchRotations));
    }

    public void resetWinchPosition() {
      m_winchOdometer.reset();
    }

    public void stopWinch() {
      m_winchMotor.stopMotor();
    }
    
}
