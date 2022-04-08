package frc.robot.utility;

public class StepController {

    //Define the array that controls the speed at each interval
    private double[][] stepControllerArray;

    /**
     * Constructor
     * 
     * @param newStepControllerArray Parameter to set the array defined above
     */
    public StepController(double[][] newStepControllerArray)
    {
        stepControllerArray = newStepControllerArray;
    }

    /**
     * Calculate the speed based on the step controller array and the error
     * 
     * @param currentPoint The current value
     * @param goal The goal value or value we are trying to get the currentPoint to
     */
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

        //Return output and switch to negative if error is negative    
        if(error < 0) output *= -1;
        return output;
    }
}
