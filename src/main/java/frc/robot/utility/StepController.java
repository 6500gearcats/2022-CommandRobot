package frc.robot.utility;

public class StepController {

    private double[][] stepControllerArray;

    public StepController(double[][] newStepControllerArray)
    {
        stepControllerArray = newStepControllerArray;
    }

    public double calculate(double currentPoint, double goal) {
        //Calculate error and define output
        double error = currentPoint - goal;
        double output = 0;

        //Go though stages to return speed
        for(int i = 0; i < stepControllerArray.length; i++) {
            if(Math.abs(error) < stepControllerArray[i][0]) {
                output = stepControllerArray[i][1];
                break;
            }
        }

        //Return output        
        if(error < 0) output *= -1;
        return output;
    }
}
