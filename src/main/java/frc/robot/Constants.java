// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.LEDSetter;

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
      }

      public static final class IntakeConstants {
        public static final int kIntakeMotorPort = 20;
        public static final I2C.Port i2cPort = I2C.Port.kMXP;
        public static final double kIntakeReverseSpeed = -0.5;
        public static final double kIntakePickupSpeed = 0.5;
        public static final int kBallPresentThreshold = 500;
        public static final double kPushBallSpeed = 0.5;
        public static final double kIntakeVomitSpeed = -0.5;
        public static final int kBlueBallThreshold = 1;
        public static final int kRedBallThreshold = 1;
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
        public static final double kMaxWinchRotations = 190;
        public static final double kMaxWinchSpeed = -1.0;
        public static final double kMaxDriveSpeed = 0.1;
        public static final int kMaxLiftCurrent = 40;
        public static final int kMaxTiltCurrent = 2;
        public static final double kFwdTiltSpeed = 0.5;
        public static final double kBackTiltSpeed = -0.25;
        public static final double kTraversalRaiseSpeed = 1;
        public static final double kTraversalTiltSpeed = 1;
        public static final double kParkSpeed = -0.05;
        public static final double kStoreArmSpeed = -0.25;
        public static final double kPrepSpeed = 0.8;
      }

      public static final class ShooterConstants {
        public static final int kShooterMotorPort = 30;
        public static final double kShooterSpeedSlow = 0.6;
        public static final double kShooterFastRPM = 3500;
        public static final int kShooterSlowRPM = 3000;
        public static final double kShooterSpeedFast = 0.8;
        public static final double kShooterSpeed = 0.9; //0.43
        public static final double kBallFiredThreshold = 0.1;
        public static final int kShooterEncoderPort = 30;
       
        public static final class IntakeLifterConstants {
        public static final int kIntakeLifterMotorPort = 22;  
        public static final double kIntakeLifterDownSpeed = -0.2;
        public static final double kIntakeLifterUpSpeed = 0.2;
        public static final double kIntakeLiftedThreshhold = 0.1;
        public static final int kLifterEncoder = 22;
        public static final int kEncoderRate = 4096;
        public static final int kMaxLifterCurrent = 20;
        public static final double kLiftRotations = 30;
      }
    }  
      


      public static final class VisionConstants {
        public static final double upperHubTargetHeight = 107;    //Height of the upper hub target in inches
        public static final double cameraHeight = 38;    //Height of the camera on the robot in inches
        public static final double cameraAngle = 26.412;    //Angle of the camera on the robot in degrees
        public static final double targetDistanceFromHub = 100;    //Target distance to be from hub for upper ball shooting in inches
        public static final double maxControllerSpeed = 0.6;
        public static final double marginForError = 5;    //The margin for error in both pitch and yaw for the shot to still be accurate
        public static final double[][] stepControllerArray = {    //Array of values that define what speed the robot should move at each interval 
            {5, 0.1},
            {15, 0.3},
            {20, 0.4},
            {50, 0.5},
            {100, 0.7},
            {150, 0.85},
            {1000, 1}
        };
        public static final double pAngularGain = 0.03;   //Proportional gain control for the angular movement of the robot 
        public static final double iAngularGain = 0.0001;   //Integral gain control for the angular movement of the robot
        public static final double dAngularGain = 0;    //Derivative gain control for the angular movement of the robot
      }

      public static final class LEDConstants {
        public static final int ledPwmPin = 0;
        public static final int ledStripLength = 51;
        public static final int[] tealRGB = new int[] {32, 227, 45};
        public static final int[] redRGB = new int[] {255, 0, 0};
        public static final int[] blueRGB = new int[] {0, 0, 255};
        public static final int[] whiteRGB = new int[] {255, 255, 100};
        public static final int[] greenRGB = new int[] {0, 255, 0};
        public static final int[] yellowRGB = new int[] {255, 255, 0};
        public static final int[] blackRGB = new int[] {0, 0, 0};
      }
}
