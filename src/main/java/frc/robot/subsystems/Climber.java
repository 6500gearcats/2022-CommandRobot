package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.FaultID;
import com.revrobotics.CANSparkMax.IdleMode;

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
  private EncoderOdometer m_tiltOdometer;

  private SparkMaxLimitSwitch m_upperLimit;
  private SparkMaxLimitSwitch m_lowerLimit;

  private boolean m_isWinchStalled = false;
  private boolean m_isTiltStalled = false;
  

    public Climber() {
      m_lowerLimit = m_winchMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
      m_upperLimit = m_winchMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);

      // m_upperLimit = m_winchMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
      // m_lowerLimit = m_winchMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);


      m_winchEncoder = m_winchMotor.getEncoder();
      m_tiltEncoder = m_tiltMotor.getEncoder();

      m_winchMotor.setInverted(true);
      m_winchMotor.setIdleMode(IdleMode.kBrake);
      m_tiltMotor.setIdleMode(IdleMode.kCoast);

      m_winchMotor.setSmartCurrentLimit(ClimberConstants.kMaxLiftCurrent);
      m_tiltMotor.setSmartCurrentLimit(ClimberConstants.kMaxTiltCurrent);

      m_winchOdometer = new EncoderOdometer(m_winchEncoder);
      m_tiltOdometer = new EncoderOdometer(m_tiltEncoder);
    }

    @Override
    public void periodic() {
      // if (m_winchMotor.get() != 0){
      //   if (m_winchOdometer.getPosition() == m_lastWinchPosition) {
      //     m_isWinchStalled = true;
      //   }
      // }

      // if (m_tiltMotor.get() != 0){
      //   if (m_tiltOdometer.getPosition() == m_lastTiltPosition) {
      //     m_isTiltStalled = true;
      //   }
      // }

      m_winchOdometer.getPosition();
      m_tiltOdometer.getPosition();
      boolean lowerLimit = m_lowerLimit.isPressed();
      boolean upperLimit = m_upperLimit.isPressed();
      SmartDashboard.putBoolean("Upper limit", upperLimit);
      SmartDashboard.putBoolean("Lower limit", lowerLimit);


      SmartDashboard.putNumber("Arm position", m_winchOdometer.getPosition());
      SmartDashboard.putNumber("Winch Motor Speed",m_winchMotor.get());
      SmartDashboard.putNumber("Tilt position", m_tiltOdometer.getPosition());
      SmartDashboard.putNumber("Tilt Motor Speed",m_tiltMotor.get());
      //if (tiltIsStalled()) stopTilt();
      if (winchIsStalled()) stopWinch();
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }

    // public void tiltArmForward(double speed) {
      
    //   m_tiltMotor.set(speed);
    // }
      private boolean m_PastRotationLimit = false;

    public void extendArm() {
      this.extendArm(ClimberConstants.kMaxWinchSpeed);
    }

    public void extendArm(double speed) {
      m_winchOdometer.reset();
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
      m_isWinchStalled = false;
    }

    public void retractArm() {
      m_winchOdometer.reset();
      m_winchMotor.set(ClimberConstants.kMaxWinchSpeed * -1);
    }

    public boolean ArmIsFullyRetracted() {
      return (m_lowerLimit.isPressed() 
          || (Math.abs(m_winchOdometer.getPosition())) > ClimberConstants.kMaxWinchRotations);

    }

    public void stopTilt() {
      m_tiltMotor.stopMotor();
      m_isTiltStalled = false;
    }

    public void tiltRobot(double speed) {
      m_tiltOdometer.reset();
      m_tiltMotor.set(speed);
    }

    public boolean winchIsStalled() {
      // We will use both motor stall detection and also whether the motor encoder value is changing.
      boolean bStalled = m_winchMotor.getFault(FaultID.kStall);
      SmartDashboard.putBoolean("Winch Moter stall", bStalled);
      SmartDashboard.putBoolean("Winch Moter rotating", m_isTiltStalled);
      return (m_isWinchStalled || bStalled);
    }

    public boolean tiltIsStalled() {
      // We will use both motor stall detection and also whether the motor encoder value is changing.
      boolean bStalled = m_tiltMotor.getFault(FaultID.kStall);
      SmartDashboard.putBoolean("Tilt Moter stall", bStalled);
      SmartDashboard.putBoolean("Tilt Moter rotating", m_isTiltStalled);
      return (m_isTiltStalled || bStalled);
    }
    
    public void ParkArm() {
      m_tiltMotor.set(ClimberConstants.kParkSpeed);
    }

    public void PrepArm() {
      m_tiltMotor.set(ClimberConstants.kPrepSpeed);
    }
}
