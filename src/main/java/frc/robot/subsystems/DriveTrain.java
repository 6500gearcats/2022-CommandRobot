// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.DriveConstants;
import frc.robot.utility.GCSlewRateLimiter;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {

  private double m_maxOutput = 0.0;
  // The motors on the left side of the drive.
  private final MotorControllerGroup m_leftMotors =
      new MotorControllerGroup(
          new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless),
          new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless));

  // The motors on the right side of the drive.
  private final MotorControllerGroup m_rightMotors =
      new MotorControllerGroup(
          new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless),
          new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless));

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  
  private GCSlewRateLimiter m_driveRateLimiter;
  private GCSlewRateLimiter m_turnRateLimiter;

  // The left-side drive encoder
  private final Encoder m_leftEncoder =
      new Encoder(
          DriveConstants.kLeftEncoderPorts[0],
          DriveConstants.kLeftEncoderPorts[1],
          DriveConstants.kLeftEncoderReversed);

  // The right-side drive encoder
  private final Encoder m_rightEncoder =
      new Encoder(
          DriveConstants.kRightEncoderPorts[0],
          DriveConstants.kRightEncoderPorts[1],
          DriveConstants.kRightEncoderReversed);
                    
  // private NetworkTableEntry m_driveSlew =
  //     tab.add("Drive Slew Rate", 0.8)
  //       .getEntry();

  // private NetworkTableEntry m_rotationSlew =
  //     tab.add("RotationSlewRate", 0.8)
  //       .getEntry();
      
  /** Creates a new DriveSubsystem. */
  public DriveTrain() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotors.setInverted(true);

    // Sets the distance per pulse for the encoders
    m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);

    m_driveRateLimiter = new GCSlewRateLimiter(DriveConstants.kDriveRateLimit);
    m_turnRateLimiter = new GCSlewRateLimiter(DriveConstants.kTurnRateLimit);

  }


  @Override
  public void periodic() {
    // double max = m_maxSpeed.getDouble(1.0);
    // this.setMaxOutput(max);

  //   double driveSlew = m_driveSlew.getDouble(1.0);

  //   double rotationSlew = m_rotationSlew.getDouble(1.0);

  //   if (driveSlew != m_driveRateLimiter.getRate()) {
  //     m_driveRateLimiter = new GCSlewRateLimiter(driveSlew, m_lastDriveInput);
  //   }

  //   if (rotationSlew != m_turnRateLimiter.getRate()) {
  //     m_turnRateLimiter = new GCSlewRateLimiter(rotationSlew, m_lastTurnInput);
  //   }
 }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {

    Double forward = m_driveRateLimiter.calculate(fwd);
    Double rotation = m_turnRateLimiter.calculate(rot);

    SmartDashboard.putNumber("Applied forward", forward);
    SmartDashboard.putNumber("Applied rotation", rotation);

    m_drive.arcadeDrive(forward, rotation);
  }

  public void driveDistance(double distance)
  {
    resetEncoders();
    arcadeDrive(0.4, 0);
    while(getAverageEncoderDistance() < distance) {
      //HOW DO I SLEEP THIS THREAD???
    }
    arcadeDrive(0, 0);
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  /**
   * Gets the average distance of the TWO encoders.
   *
   * @return the average of the TWO encoder readings
   */
  public double getAverageEncoderDistance() {
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public Encoder getLeftEncoder() {
    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_maxOutput = maxOutput;
    SmartDashboard.putNumber("DriveTrain Max Output Set", m_maxOutput);
    m_drive.setMaxOutput(maxOutput);
  }

}