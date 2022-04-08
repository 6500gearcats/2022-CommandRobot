package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDSetter {
    
    //Define addressable led and its buffer
    private static AddressableLED ledStrip;
    private static AddressableLEDBuffer ledBuffer;
    
    /**
     * Set the pin and length
     * 
     * @param pwmPin PWM pin that the stip connects to
     * @param stripLength Length of the LED strip
     */
    public static void setPinAndLength(int pwmPin, int stripLength) {
        //Define the addressable led and set the legnth
        ledStrip = new AddressableLED(pwmPin);
        ledStrip.setLength(stripLength);

        //Define the buffer
        ledBuffer = new AddressableLEDBuffer(stripLength);

        //Start the strip
        ledStrip.start();
    }

    /**
     * Iterate though each led in the strip and set its color
     * 
     * @param red Red value for the strip
     * @param green Green value for the strip
     * @param blue Blue value for the strip
     */
    public static void setEntireStripColor(int[] color) {
        //Set data for buffer
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, color[0], color[1], color[2]);
         }

        //Write data to led strip
         ledStrip.setData(ledBuffer);
    }

    /**
     * Stop the LED strip object
     */
    public static void stop() {
        ledStrip.stop();
    }

}