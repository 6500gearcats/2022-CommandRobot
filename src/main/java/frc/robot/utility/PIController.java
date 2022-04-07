package frc.robot.utility;
import java.lang.Math;

import frc.robot.Constants;

public class PIController {

    private double pGain;

    private double maxSpeed = Constants.VisionConstants.maxControllerSpeed;

    public PIController(double pGain) {
        this.pGain = pGain;
    }

    public double calculate(double setpoint, double goal) {
        double error = goal - setpoint;

        //Calculate output
        double output = Math.cbrt(error)/20;

        System.out.println(output);

        //Clamp value
        if(Math.abs(output) > maxSpeed) output = -maxSpeed;

        return output;
    }
    
}
