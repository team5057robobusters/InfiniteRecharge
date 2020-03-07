package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class HookMechanism {
    /**
     * Creates a new HookMechanism.
     */
    private DoubleSolenoid piston;
    public boolean hook;
    public HookMechanism() {
      piston = new DoubleSolenoid(1, 1);
      hook = true;
    }
  
    public void checkButton(XboxController xbox) {
      //Original code for the hook mechanism with two buttons
      /*if (xbox.getAButtonPressed() == true) {
        piston.set(Value.kForward);
      }
      else if (xbox.getYButtonPressed() == true) {
        piston.set(Value.kReverse);
      }
      */
      //Newer code for the hook mechanism where one button controls both the up and the down
      if (xbox.getBButtonPressed() == true) {
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