package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
public class HookMechanism {
    /**
     * Creates a new HookMechanism.
     */
    private DoubleSolenoid piston;
    public XboxController xbox;
    public boolean hook;
    public HookMechanism() {
      xbox = new XboxController(HookMechanismConstants.xboxPort);
      piston = new DoubleSolenoid(HookMechanismConstants.pistonForewordChanel, HookMechanismConstants.pistonReverseChanel);
      hook = true;
    }
  
    public void checkButton() {
      //Original code for the hook mechanism with two buttons
      /*if (xbox.getAButtonPressed() == true) {
        piston.set(Value.kForward);
      }
      else if (xbox.getYButtonPressed() == true) {
        piston.set(Value.kReverse);
      }
      */
      //Newer code for the hook mechanism where one button controls both the up and the down
      if (xbox.getAButtonPressed() == true) {
        if (hook == true) {
          piston.set(Value.kForward);
          hook = false;
        }
        else {
          piston.set(Value.kReverse);
          hook = true;
        }
      }
    }
  }
