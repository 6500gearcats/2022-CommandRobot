// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

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
      }

      public static final class IntakeConstants {
        public static final int kIntakeMotorPort = 20;
        public static final I2C.Port i2cPort = I2C.Port.kMXP;
        public static final double kIntakeReverseSpeed = -0.5;
        public static final double kIntakePickupSpeed = 0.5;
        public static final int kBallPresentThreshold = 0;
      }

      public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
      }

}
