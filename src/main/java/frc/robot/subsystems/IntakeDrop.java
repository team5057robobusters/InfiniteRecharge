package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Zach Sussman
 * 
 */
 
public class IntakeDrop {
    private DoubleSolenoid intakeLift;

    public IntakeDrop(int portNumber)
    {
        intakeLift = new DoubleSolenoid(portNumber,1);
    }
}