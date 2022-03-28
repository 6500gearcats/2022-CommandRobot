// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.numbers.N2;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.XboxController.Button;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class DriveConstants {
        public static final int kLeftMotor1Port = 1;
        public static final int kLeftMotor2Port = 3;
        public static final int kRightMotor1Port = 2;
        public static final int kRightMotor2Port = 4;
    
        public static final int[] kLeftEncoderPorts = new int[] {1, 3};
        public static final int[] kRightEncoderPorts = new int[] {2, 4};
        public static final boolean kLeftEncoderReversed = false;
        public static final boolean kRightEncoderReversed = true;
    
        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterInches = 6;
        public static final double kEncoderDistancePerPulse =
            // Assumes the encoders are directly mounted on the wheel shafts
            (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
        public static final double kMaxSpeed = 1;
        public static final double kSlowSpeed = 0.2;
        public static final double kDriveRateLimit = 2;
        public static final double kTurnRateLimit = 20;
        public static final double kAutoSpeed = -0.5;
        public static final boolean kGyroReversed = false;
        public static final int kVisionFilterSamples = 10;
        public static final double kTurnP = 1;
        public static final double kTurnD = 0;
        public static final double kTurnI = 0;
        public static final double kTurnToleranceDeg = 5;
        public static final double kTurnRateToleranceDegPerS = 10;
        
        // // Simulation constants
        // public static final double kS = 0.62; // kS
        // public static final double kV = 2.42; // kV
        // public static final double kA = 0.165; // kA
        // public static final double kVAngular = 1.654294727 * 2;
        // public static final double kAAngular = kA; // ???????

        // public static final LinearSystem<N2, N2, N2> PLANT =         
        //   LinearSystemId.identifyDrivetrainSystem(kV, kA, kVAngular, kAAngular);

        // public static final double TRACK_WIDTH_METERS = 0.5757943419; // About 22.6" from characterization, was 22" from CAD
        // public static final double DRIVE_GEARING = 10.71;
        // public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(6);
        // public static final double WHEEL_RADIUS_METERS = WHEEL_DIAMETER_METERS / 2.0d;
      }

      public static final class IntakeConstants {
        public static final int kIntakeMotorPort = 20;
        public static final I2C.Port i2cPort = I2C.Port.kMXP;
        public static final double kIntakeReverseSpeed = -0.5;
        public static final double kIntakePickupSpeed = 0.5;
        public static final int kBallPresentThreshold = 500;
        public static final double kPushBallSpeed = 0.5;
        public static final double kIntakeVomitSpeed = -0.5;
      }

      public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kClimberControllerPort = 1;
        public static final int kPickUpBallBinding = Button.kA.value;
        public static final int kLiftBallBinding = Button.kX.value;
        public static final int kSlowModeTrigger = Button.kLeftBumper.value;
        public static final int kFireShooter = Button.kRightBumper.value;
      }

      public static final class ElevatorConstants {
        public static final int kElevatorMotorPort = 21;
        public static final double kElevatorSpeed = -0.5;
        public static final int kTopSwitchChannel = 0;
        public static final double kPushBall = 0.5;
      }

      public static final class ClimberConstants {
        public static final int kTiltMotorPort = 10;
        public static final int kWinchMotorPort = 11;    
        public static final double kDefaultTiltSpeed = 0.5;
        public static final double kMaxWinchRotations = 330;
        public static final double kMaxWinchSpeed = -1.0;
        public static final double kMaxDriveSpeed = 0.1;
        public static final int kMaxLiftCurrent = 30;
        public static final int kMaxTiltCurrent = 2;
        public static final double kFwdTiltSpeed = 0.5;
        public static final double kBackTiltSpeed = -0.5;
        public static final double kTraversalRaiseSpeed = 1;
        public static final double kTraversalTiltSpeed = 1;
        public static final double kParkSpeed = 0.05;
      }

      public static final class ShooterConstants {
        public static final int kShooterMotorPort = 30;
        public static final double kShooterSpeedSlow = 0.43;
        public static final double kShooterSpeedFast = 0.70;
        public static final double kShooterSpeed = 0.85; //0.43
        public static final double kBallFiredThreshold = 0.1;
        public static final int kShooterEncoderPort = 30;
        
      }
}
