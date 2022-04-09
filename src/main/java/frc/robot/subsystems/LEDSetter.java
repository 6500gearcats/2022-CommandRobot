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
    public LEDSetter(int pwmPin, int stripLength) {
        setPinAndLength(pwmPin, stripLength);
    }

    public void setPinAndLength(int pwmPin, int stripLength) {
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
     * Set the a specific number of LEDS on each end to a specified color
     * 
     * @param numberFromEnd Number of LEDS on each end of the strip to set to the endColor
     * @param endColor The color to set the LEDS on the end of each strip
     * @param middleColor The color to set the middle LEDS
     */
    public static void setStripEndColor(int numberFromEnd, int[] endColor, int[] middleColor) {

        //Define indexes
        int startOfStripSetIndex = numberFromEnd;
        int endOfStripSetIndex = ledBuffer.getLength() - numberFromEnd - 1;

        //Set data for buffer
        for (var i = 0; i < ledBuffer.getLength(); i++) {

            if(i < startOfStripSetIndex || i > endOfStripSetIndex) {
                ledBuffer.setRGB(i, endColor[0], endColor[1], endColor[2]);
            } else {
                ledBuffer.setRGB(i, middleColor[0], middleColor[1], middleColor[2]);
            }
         }

        //Write data to led strip
         ledStrip.setData(ledBuffer);
    }

    /**
     * Set the a specific number of LEDS on each end to a specified color
     * 
     * @param numberFromEnd Number of LEDS on each end of the strip to set to the endColor
     * @param endColor The color to set the LEDS on the end of each strip
     * @param middleColor The color to set the middle LEDS
     */
    public static void setPercentageOfStrip(double percentage, int[] firstColor, int[] secondColor) {

        //Define indexes
        int changeColorIndex = (int) Math.round((ledBuffer.getLength() - 1) * percentage);

        //Set data for buffer
        for (var i = 0; i < ledBuffer.getLength(); i++) {

            if(i < changeColorIndex) {
                ledBuffer.setRGB(i, firstColor[0], firstColor[1], firstColor[2]);
            } else {
                ledBuffer.setRGB(i, secondColor[0], secondColor[1], secondColor[2]);
            }
         }

        //Write data to led strip
         ledStrip.setData(ledBuffer);
    }

    /**
     * Stop the LED strip object
     */
    public void stop() {
        ledStrip.stop();
    }

}