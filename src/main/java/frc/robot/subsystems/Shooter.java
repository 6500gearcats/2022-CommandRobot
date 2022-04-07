package frc.robot.subsystems;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Shooter extends PIDSubsystem {
  private final CANSparkMax m_shooterMotor = new CANSparkMax(ShooterConstants.kShooterMotorPort, MotorType.kBrushless);
  private final RelativeEncoder m_shooterEncoder = m_shooterMotor.getEncoder();

  private final SimpleMotorFeedforward m_shooterFeedforward =
      new SimpleMotorFeedforward(
          ShooterConstants.kSVolts, ShooterConstants.kVVoltSecondsPerRotation);

  /** The shooter subsystem for the robot. */
  public Shooter() {
    super(new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD));
    getController().setTolerance(ShooterConstants.kShooterToleranceRPS);
    setSetpoint(ShooterConstants.kShooterFastTargetRPS);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    double newoutput = output + m_shooterFeedforward.calculate(setpoint);
    m_shooterMotor.setVoltage(newoutput);
    SmartDashboard.putNumber("ShooterPID output", output);
    SmartDashboard.putNumber("ShooterPID new output", newoutput);
    SmartDashboard.putNumber("ShooterPID setpoint", setpoint);
  }

  @Override
  public double getMeasurement() {
    return m_shooterEncoder.getVelocity();
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("ShooterPID rotation", getMeasurement());
    
  }


}