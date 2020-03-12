package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake extends SubsystemBase {
  /**
   * Creates a new HookMechanism.
   */
  private DoubleSolenoid piston;
  public XboxController xbox;
  public final int forwardChannel, reverseChannel;
  public Victor intakeMotor1;
  public Victor intakeMotor2;
  public boolean isOn;
  public Intake(int fc, int rc, int v1, int v2) {
    isOn = false;
    forwardChannel = fc;
    reverseChannel = rc;
    xbox = new XboxController(0);
    piston = new DoubleSolenoid(forwardChannel, reverseChannel);
    intakeMotor1 = new Victor(v1);
    intakeMotor2 = new Victor(v2);
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
  public void turnOn(boolean invert) {
    piston.set(Value.kForward);
    intakeMotor1.set(-1);
    intakeMotor2.set(1);
  }
  public void turnOff() {
    piston.set(Value.kReverse);
    intakeMotor1.set(0);
    intakeMotor2.set(0);
  }

}