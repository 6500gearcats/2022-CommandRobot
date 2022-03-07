// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
          public static int m_shooterMotor1Port;
    }
    public static final class ShooterConstants { 

        //TODO. Have to find the encoder ports.
        public static final int[] kShooterEncoderPorts = new int[] {0, 1};
//TODO. Have to test this to see if it goes in the right direction.        
        public static final boolean kEncoderReversed = false;
        public static final double kSVolts = 0.05;
        public static final double kShooterFreeRPS = 5300;
        public static final double kShooterTargetRPS = 4000;
        public static final double kShooterToleranceRPS = 50;
    public static final double kVVoltSecondsPerRotation =
        // Should have value 12V at free speed...
        12.0 / kShooterFreeRPS;
    // These are not real PID gains, and will have to be tuned for your specific robot.
    public static final double kP = 1;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
    public static Object kFeederSpeed;
    }
      public static final class OIConstants {
        public static final int kDriverControllerPort = 0;

      }

}
