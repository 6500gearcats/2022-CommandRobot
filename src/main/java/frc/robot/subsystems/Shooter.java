package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ShooterConstants;
public class Shooter extends PIDSubsystem{
  private final MotorController m_shooterMotor=
  new CANSparkMax(DriveConstants.m_shooterMotor1Port, MotorType.kBrushless);
  private final Encoder m_shooterEncoder =
  new Encoder(
      ShooterConstants.kShooterEncoderPorts[0],
      ShooterConstants.kShooterEncoderPorts[1],
      ShooterConstants.kEncoderReversed);
  private final SimpleMotorFeedforward m_shooterFeedForward =
  new SimpleMotorFeedforward(
    ShooterConstants.kSVolts, ShooterConstants.kVVoltSecondsPerRotation);

    public Shooter() {
      super(new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD));
    getController().setTolerance(ShooterConstants.kShooterToleranceRPS);
    m_shooterEncoder.setDistancePerPulse(ShooterConstants.kEncoderDistancePerPulse);
    setSetpoint(ShooterConstants.kShooterTargetRPS);
  }

    @Override
    public void useOutput(double output, double setpoint) {
    m_shooterMotor.setVoltage(output + m_shooterFeedForward.calculate(setpoint));
    }

    public boolean atSetpoint() {
      return m_controller.atSetpoint();
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }
    @Override
    protected double getMeasurement() {
      return m_shooterEncoder.getRate();
    }

    }
