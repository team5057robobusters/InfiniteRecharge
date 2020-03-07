package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake extends SubsystemBase {
  /**
   * Creates a new HookMechanism.
   */
  private DoubleSolenoid piston;
  public XboxController xbox;
  public final int forwardChannel, reverseChannel;
  public Victor intakeMotor;
  public boolean isOn;
  public Intake(int fc, int rc, int v) {
    isOn = false;
    forwardChannel = fc;
    reverseChannel = rc;
    xbox = new XboxController(0);
    piston = new DoubleSolenoid(forwardChannel, reverseChannel);
    intakeMotor = new Victor(v);
  }

  @Override
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
  public void turnOn(boolean invert) {
    piston.set(Value.kForward);
    intakeMotor.set(invert?-1:1);
  }
  public void turnOff() {
    piston.set(Value.kReverse);
    intakeMotor.set(0);
  }

}