package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class Elevator extends SubsystemBase{
  public final MotorController m_elevatorMotor = new CANSparkMax(ElevatorConstants.kElevatorMotorPort, MotorType.kBrushed);
  public final DigitalInput m_topSwitch = new DigitalInput(ElevatorConstants.kTopSwitchChannel);

  private final ColorSensorV3 m_lowerColorSensor = new ColorSensorV3(ElevatorConstants.i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  public boolean m_wrongBall = false;


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
      return !(m_topSwitch.get());

    }

    public void stop() {
      m_elevatorMotor.stopMotor();
    }

    public boolean isLoaded() {
        return isBallAtTop();
    }

    public void detectColor()
    {
      m_colorMatcher.addColorMatch(ElevatorConstants.kBlueTarget);
      m_colorMatcher.addColorMatch(ElevatorConstants.kGreenTarget);
      m_colorMatcher.addColorMatch(ElevatorConstants.kRedTarget);
      m_colorMatcher.addColorMatch(ElevatorConstants.kYellowTarget);

      Color detectedColor = m_lowerColorSensor.getColor();

      double IR = m_lowerColorSensor.getIR();

      SmartDashboard.putNumber("Red", detectedColor.red);
      SmartDashboard.putNumber("Green", detectedColor.green);
      SmartDashboard.putNumber("Blue", detectedColor.blue);
      SmartDashboard.putNumber("IR", IR);

      int proximity = m_lowerColorSensor.getProximity();

      SmartDashboard.putNumber("Proximity", proximity);

      String colorString;
      ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

      if (match.color == ElevatorConstants.kBlueTarget) {
        colorString = "Blue";
      } else if (match.color == ElevatorConstants.kRedTarget) {
        colorString = "Red";
      } else if (match.color == ElevatorConstants.kGreenTarget) {
        colorString = "Green";
      } else if (match.color == ElevatorConstants.kYellowTarget) {
        colorString = "Yellow";
      } else {
        colorString = "Unknown";
      }

      if (colorString != "Red")
      {
        m_wrongBall = true;
      }
      else
      {
        m_wrongBall = false;
      }

      SmartDashboard.putNumber("Red", detectedColor.red);
      SmartDashboard.putNumber("Green", detectedColor.green);
      SmartDashboard.putNumber("Blue", detectedColor.blue);
      SmartDashboard.putNumber("Confidence", match.confidence);
      SmartDashboard.putString("Detected Color", colorString);
    }
    
}
