package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Intake extends SubsystemBase {
  /**
   * Creates a new HookMechanism.
   */
  private DoubleSolenoid piston;
  public XboxController xbox;
  public final int forwardChannel, reverseChannel;
  public Victor intakeMotor1;
  public Victor intakeMotor2;
  public Victor intakeMotor3;
  public boolean isOn;
  public boolean isDown;
  public Intake(int fc, int rc, int v1, int v2) {
    isOn = false;
    forwardChannel = fc;
    reverseChannel = rc;
    xbox = new XboxController(0);
    piston = new DoubleSolenoid(forwardChannel, reverseChannel);
    intakeMotor1 = new Victor(v1);
    intakeMotor2 = new Victor(v2);
    intakeMotor3 = new Victor(6);
  }

  /*@Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (xbox.getYButtonPressed()) {
      if (isOn) {
        turnOff();
        isOn = false;
      }
      else {
        turnOn(false);
        isOn = true;
      }
    }
  }
  */
  
  public void checkButton(XboxController xboxControl){
    if (xboxControl.getBButtonPressed()) {
      if (isDown) {
        piston.set(Value.kReverse);
        isDown = false;
      }
      else {
        piston.set(Value.kForward);
        isDown = true;
      }
    }
    if (xboxControl.getTriggerAxis(Hand.kLeft)>.2) {
      if (isOn) {
        isOn = false;
        intakeMotor1.set(0);
        intakeMotor2.set(0);
        intakeMotor3.set(0);
      }
      else {
        intakeMotor1.set(-xboxControl.getTriggerAxis(Hand.kLeft));
        intakeMotor2.set(xboxControl.getTriggerAxis(Hand.kLeft));
        intakeMotor3.set(-xboxControl.getTriggerAxis(Hand.kLeft));
        isOn = true;
      
      }
    }
  }

}