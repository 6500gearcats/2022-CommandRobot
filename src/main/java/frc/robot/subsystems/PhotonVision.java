package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import edu.wpi.first.math.controller.PIDController;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants;
import java.lang.Math;

public class PhotonVision extends SubsystemBase {
    
    //Define PhotonVision camera
    private PhotonCamera camera = new PhotonCamera("Camera1");

    //Define PhotonVision vars
    private boolean targetInSights = false;
    private double targetYaw;
    private double targetPitch;

    //Create PID controller with constants from constants file
    PIDController fowardsController = new PIDController(
        Constants.VisionConstants.pLinearGain, 
        Constants.VisionConstants.iLinearGain, 
        Constants.VisionConstants.dLinearGain
    );
    PIDController rotationController = new PIDController(
        Constants.VisionConstants.pAngularGain, 
        Constants.VisionConstants.iAngularGain, 
        Constants.VisionConstants.dAngularGain
    );



    //Class that contains the fowards speed and rotation speed to be assigned to arcade drive to move the robot
    public class arcadeDriveSpeeds {
        //Speed vars
        private double fowardSpeed;
        private double rotationSpeed;
        private boolean applySaefty = true;
        private double maxSpeed;



        /**
         * Constructor
         * 
         * @param fowardSpeed Fowards speed
         * @param rotationSpeed Rotation speed
         */
        public arcadeDriveSpeeds(double fowardSpeed, double rotationSpeed) {
            this.maxSpeed = 0.5;
            this.fowardSpeed = fowardSpeed;
            this.rotationSpeed = rotationSpeed;
        }



        /**
         * Constructor
         * 
         * @param fowardSpeed Fowards speed
         * @param rotationSpeed Rotation speed
         * @param maxSpeed Max speed the robot should go
         */
        public arcadeDriveSpeeds(double fowardSpeed, double rotationSpeed, double maxSpeed) {
            this.maxSpeed = maxSpeed;
            this.fowardSpeed = fowardSpeed;
            this.rotationSpeed = rotationSpeed;
        }



        /**
         * Disable saefty
         */
        public void disableSaefty() {
            applySaefty = false;
        }
 


        /**
         * Get the fowards speed and apply saefty if it is on
         * 
         * @return Fowards speed
         */
        public double getFowardSpeed() {
            if(applySaefty && fowardSpeed > maxSpeed) return maxSpeed;
            if(applySaefty && fowardSpeed < -maxSpeed) return -maxSpeed;
            return fowardSpeed;
        }



        /**
         * Get the rotation speed and apply saefty if it is on
         * 
         * @return Rotation speed
         */
        public double getRotationSpeed() {
            if(applySaefty && rotationSpeed > maxSpeed) return maxSpeed;
            if(applySaefty && rotationSpeed < -maxSpeed) return -maxSpeed;
            return rotationSpeed;
        }



        /**
         * Get fowards and rotation value as strings
         * 
         * @return String that contains fowards and rotation speeds
         */
        public String toString() {
            return "ARCADE DRIVE SPEEDS: [Fowards=" + fowardSpeed + " Rotation=" + rotationSpeed + "]";
        }
    }



    /**
     * Call periodicly
     * Update results from PhotonVision
    */
    @Override
    public void periodic() {
        //Get latest results from camera and set best target
        var result = camera.getLatestResult();

        //If there are targets
        if(result.hasTargets()) {

            //Get best target and set targetInSights to true
            targetInSights = true;
            PhotonTrackedTarget bestTarget = result.getBestTarget();

            //Assign yaw and pitch to best target yaw and pitch
            targetYaw = bestTarget.getYaw();
            targetPitch = bestTarget.getPitch();

        // If there are no targets, print and stop drive
        } else {
            targetInSights = false;
        }
    }



    /**
     * Print values returned from photon vision for debugging
     */
    public String pidValuesAsString() {
        return "PID VALUES: [Fowards error: [postion=" + fowardsController.getPositionError() + ", velocity=" + fowardsController.getVelocityError() +
        "], Rotation error: [postion = " + rotationController.getPositionError() + ", velocity=" + rotationController.getVelocityError() + "]]";
    }



    /**
     * Print values returned from photon vision for debugging
     */
    public String photonVisionValuesAsString() {
        return "PHOTONVISION VALUES: [Found Target=" + targetInSights + ", Pitch=" + targetPitch + ", Yaw=" + targetYaw + "]";
    }


    
    /**
     * Calculate distance to target
     * 
     * @return Distance in inches to the target
     */
    public double getDistanceToTarget() {
        //Get constants from constants file and convert to meters
        double upperHubTargetHeight = Units.inchesToMeters(Constants.VisionConstants.upperHubTargetHeight);
        double cameraHeight = Units.inchesToMeters(Constants.VisionConstants.cameraHeight);
        double cameraAngle = Constants.VisionConstants.cameraAngle;

        //Return distace
        return Units.metersToInches(
            (upperHubTargetHeight - cameraHeight) / Math.tan(Math.toRadians(targetPitch + cameraAngle))
        );
    }



    /** 
     * Gets the speeds that arcade drive should be assigned to get to the target spot and aim at the target
     * 
     * @return Arcade speed class containing the speed values required
    */
    public arcadeDriveSpeeds getArcadeSpeed() {

        //If there is a target
        if(targetInSights) {

            //Use PID controllers to calculate the speed required
            double fowardSpeed = -fowardsController.calculate(getDistanceToTarget(), Constants.VisionConstants.targetDistanceFromHub);
            double rotationSpeed = -rotationController.calculate(targetYaw, 0);

            //Return speeds
            return new arcadeDriveSpeeds(fowardSpeed, rotationSpeed);

        //If there is no target then set then dont drive
        } else {
            return new arcadeDriveSpeeds(0, 0);
        }
    }


    
    /**
     * Returns the yaw
     * 
     * @return Target yaw
     */
    public double getYaw() {
        return targetYaw;
    }



    /**
     * Returns the pitch
     * 
     * @return Target pitch
     */
    public double getPitch() {
        return targetPitch;
    }

}