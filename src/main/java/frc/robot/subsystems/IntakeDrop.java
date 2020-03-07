package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import frc.robot.Constants;
/**
 *
 * @author Zach Sussman
 * 
 */
 
public class IntakeDrop {
    private DoubleSolenoid intakeLift;

    public IntakeDrop()
    {
        intakeLift = new DoubleSolenoid(Constants.IntakeDropConstants.intakeLiftPortForwardChannel,
                        Constants.IntakeDropConstants.intakeLiftPortReverseChannel);
    }
}
