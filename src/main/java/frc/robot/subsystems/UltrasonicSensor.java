package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Zach Sussman
 * 
 */
 
public class UltrasonicSensor {
    
    // The handle to access the sensor
    private final AnalogInput rangefinder;
    
    // The scaling factor:  distance in centimeters = volts returned / SCALING_FACTOR
    private final int SCALING_FACTOR = 512/5*24/23;
    
    /** Creates a new ultrasonic sensor hooked up to <code>portNumber</code> on the analog breakout.
     * @params portNumber The port number on the breakout.
     */
    public UltrasonicSensor(int portNumber){
        rangefinder = new AnalogInput(portNumber);
        rangefinder.setOversampleBits(2); // Completely arbitrary
        rangefinder.setAverageBits(2); // Ditto
    }
    
    /** Returns the distance measured in centimeter.  */
    public double getInches(){
        double volts = rangefinder.getAverageVoltage();
        return (double) (volts * SCALING_FACTOR);
    }
    
    /** Returns the distance measured in feet.  */
    public double getFeet(){
        return this.getInches() / 12.0;
    }
    
    /** Returns the distance measured as "feet<code>'</code> inches<code>"</code>".  */
    
    public String getReadable(){
        return (int)getFeet() + "' " + (int)(getInches() - (int)getFeet()*12) + "\"";
    }
    
    public void setLightState(Relay distanceLight) {
        //takes a relay as input and changes the values depending on distance to target
        
        if(this.getInches() > 45 && this.getInches() < 51) {
        distanceLight.set(Relay.Value.kForward);
        }
        else if(this.getInches() > 69 && this.getInches() < 75) {
        distanceLight.set(Relay.Value.kReverse);
        }
        else if(this.getInches() > 93 && this.getInches() < 99) {
        distanceLight.set(Relay.Value.kOn);
        }
        else {
        distanceLight.set(Relay.Value.kOff);
        }
    }
}