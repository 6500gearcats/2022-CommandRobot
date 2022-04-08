package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDSetter {
    
    //Define addressable led and its buffer
    private AddressableLED ledStrip;
    private AddressableLEDBuffer ledBuffer;

    /**
     * Constructor
     * 
     * @param pwmPin PWM pin that the stip connects to
     * @param stripLength Length of the LED strip
     */
    public LEDSetter(int pwmPin, int stripLength) {
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
    public void setEntireStripColor(int red, int green, int blue) {
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, red, green, blue);
         }
    }

    /**
     * Stop the LED strip object
     */
    public void stop() {
        ledStrip.stop();
    }

}