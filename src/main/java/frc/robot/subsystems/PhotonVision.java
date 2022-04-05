package frc.robot.subsystems;

import org.photonvision.*;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
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

    //Init function
    public PhotonVision() {
    
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

            targetYaw = bestTarget.getYaw();
            targetPitch = bestTarget.getPitch();

        // If there are no targets, print and stop drive
        } else {
            targetInSights = false;
        }
    }

    //Pring values returned from photon vision for debugging
    public void printPhotonVisionValues() {
        System.out.println(
            "Found Target: " + targetInSights +
            "\nPitch: " + targetPitch +
            "\nYaw: " + targetYaw
        );
    }

    //Calculate distance to target
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
    
    private double translate(double value, double oldMin, double oldMax, double newMin, double newMax) {
        //Figure out how wide each range is
        double oldSpan = oldMax - oldMin;
        double newSpan = newMax - newMin;
    
        //Convert the left range into a 0-1 range
        double valueScaled = (value - oldMin) / oldSpan;
    
        //Convert the 0-1 range into a value in the right range
        return newMin + (valueScaled * newSpan);
    }

}
