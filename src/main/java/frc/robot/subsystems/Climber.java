package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMax.FaultID;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimberConstants;
import frc.robot.utility.EncoderOdometer;

public class Climber extends SubsystemBase{
  public final CANSparkMax m_winchMotor = new CANSparkMax(ClimberConstants.kWinchMotorPort, MotorType.kBrushless);
  public final CANSparkMax m_tiltMotor = new CANSparkMax(ClimberConstants.kTiltMotorPort, MotorType.kBrushless);


  //private RelativeEncoder m_winchEncoder;
  private RelativeEncoder m_winchEncoder;
  private RelativeEncoder m_tiltEncoder;
  private EncoderOdometer m_winchOdometer;

  private SparkMaxLimitSwitch m_upperLimit;
  private SparkMaxLimitSwitch m_lowerLimit;

  private double m_lastWinchPosition; // update each periodic - use to detect stall
  private boolean m_isStalled = false;

    public Climber() {
      m_lowerLimit = m_winchMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
      m_upperLimit = m_winchMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);

      m_winchEncoder = m_winchMotor.getEncoder();
      m_tiltEncoder = m_tiltMotor.getEncoder();

      //m_winchMotor.setInverted(true);
      m_winchMotor.setIdleMode(IdleMode.kBrake);
      m_tiltMotor.setIdleMode(IdleMode.kBrake);

      m_winchMotor.setSmartCurrentLimit(ClimberConstants.kMaxLiftCurrent);
      m_tiltMotor.setSmartCurrentLimit(ClimberConstants.kMaxTiltCurrent);

      m_winchOdometer = new EncoderOdometer(m_winchEncoder);
    }

    @Override
    public void periodic() {
      if (m_winchMotor.get() != 0){
        if (m_winchOdometer.getPosition() == m_lastWinchPosition) {
          m_isStalled = true;
        }
      }
      // This method will be called once per scheduler run
      m_lastWinchPosition = m_winchOdometer.getPosition();
      SmartDashboard.putNumber("Arm position", m_winchOdometer.getPosition());
      SmartDashboard.putNumber("Winch Motor Speed",m_winchMotor.get());
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
      boolean lowerLimit = m_lowerLimit.isPressed();
      boolean upperLimit = m_upperLimit.isPressed();
      SmartDashboard.putBoolean("Upper limit", upperLimit);
      SmartDashboard.putBoolean("Lower limit", lowerLimit);

      return (m_upperLimit.isPressed() 
          || (Math.abs(m_winchOdometer.getPosition())) > ClimberConstants.kMaxWinchRotations);
    }

    public void resetWinchPosition() {
      m_winchOdometer.reset();
    }

    public void stopWinch() {
      m_winchMotor.stopMotor();
      m_isStalled = false;
    }

    public void retractArm() {
      m_winchOdometer.reset();
      m_winchMotor.set(ClimberConstants.kMaxWinchSpeed * -1);
    }

    public boolean ArmIsFullyRetracted() {
      boolean lowerLimit = m_lowerLimit.isPressed();
      boolean upperLimit = m_upperLimit.isPressed();
      SmartDashboard.putBoolean("Upper limit", upperLimit);
      SmartDashboard.putBoolean("Lower limit", lowerLimit);

      return (m_lowerLimit.isPressed() 
          || (Math.abs(m_winchOdometer.getPosition())) > ClimberConstants.kMaxWinchRotations);

    }

    public void stopTilt() {
      m_tiltMotor.stopMotor();
    }

    public void tiltRobot(double speed) {
      m_tiltMotor.set(speed);
    }

    public boolean WinchIsStalled() {
      // We will use both motor stall detection and also whether the motor encoder value is changing.
      boolean bStalled = m_winchMotor.getFault(FaultID.kStall);
      return (m_isStalled || bStalled);
    }
    
}
