package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
public class Intake extends SubsystemBase {
  /**
   * Creates a new HookMechanism.
   */
  private DoubleSolenoid piston;
  public XboxController xbox;
  
  public Victor intakeMotor;
  public boolean isOn;
  public Intake() {
    isOn = false;
    
    xbox = new XboxController(Constants.xboxPort);
    piston = new DoubleSolenoid(Constants.IntakeConstants.forwardChannel, Constants.IntakeConstants.reverseChannel);
    intakeMotor = new Victor(Constants.IntakeConstants.victorPort);
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
