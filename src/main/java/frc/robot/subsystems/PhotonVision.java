package frc.robot.subsystems;

import org.photonvision.*;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.util.Units;
import frc.robot.subsystems.Intake;
import frc.robot.Constants;
import java.lang.Math;

public class PhotonVision extends SubsystemBase {
    
    //Define PhotonVision camera
    private PhotonCamera camera = new PhotonCamera("Camera1");

    //Define drive
    private final DriveTrain m_drive;
    private final Intake m_intake;


    //Define PhotonVision vars
    private boolean targetInSights = false;
    private double targetYaw;
    private double targetPitch;

    //Init function
    public PhotonVision(DriveTrain drive, Intake intake) {
        
        //Set drive and intake to parameters
        m_drive = drive;
        m_intake = intake;

        //Set drive max
        m_drive.setMaxOutput(DriveConstants.kMaxSpeed);
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

    //Calculate distance to target
    public double getDistanceToTarget() {
        
        //Get constants from constants file and convert to meters
        double upperHubTargetHeight = Units.inchesToMeters(Constants.VisionConstants.upperHubTargetHeight);
        double cameraHeight = Units.inchesToMeters(Constants.VisionConstants.cameraHeight);

        System.out.println("Pitch: " + targetPitch);
        System.out.println("Height Difference: " + (upperHubTargetHeight - cameraHeight));
        System.out.println("Distance in Meters: " + (upperHubTargetHeight - cameraHeight) / Math.tan(Math.toRadians(targetPitch)));
        System.out.println("PhotonVision: " + PhotonUtils.calculateDistanceToTargetMeters(cameraHeight, upperHubTargetHeight, 0, Math.toRadians(targetPitch)));

        //Return distace
        return Units.metersToInches(
            (upperHubTargetHeight - cameraHeight) / Math.tan(Math.toRadians(targetPitch))
        );
    }

    //Drive robot to shoot at the upper hub
    public void moveToShootUpper()
    {
        while(true)
        {
            
        }
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
