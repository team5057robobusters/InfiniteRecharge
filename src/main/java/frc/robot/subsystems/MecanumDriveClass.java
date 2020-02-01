package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MecanumDriveClass extends SubsystemBase {

    private final VictorSP FrontLeftMotor = new VictorSP(Constants.DriveConstants.frontLeftMotorPwmPort);
    private final VictorSP FrontRightMotor = new VictorSP(Constants.DriveConstants.frontRightMotorPwmPort);
    private final VictorSP RearLeftMotor = new VictorSP(Constants.DriveConstants.rearLeftMotorPwmPort);
    private final VictorSP RearRightMotor = new VictorSP(Constants.DriveConstants.rearRightMotorPwmPort);

    private MecanumDrive DriveController;

    //Upon initilization of new MecanumDriveClass set the following variables
    public MecanumDriveClass(){
        FrontRightMotor.setInverted(true);
        RearLeftMotor.setInverted(true);
        DriveController = new MecanumDrive(FrontLeftMotor, RearLeftMotor, FrontRightMotor, RearRightMotor);
    }

    //Call this method in TelopPeriodic
    public void Drive(double ySpeed, double xSpeed, double zRotation)
    {
        DriveController.driveCartesian(
            ySpeed*Constants.DriveConstants.throttlevalue
            ,-xSpeed*Constants.DriveConstants.throttlevalue
            ,zRotation);
    }

    //Testing Method for Forward and Calibration
    public void TestForward()
    {
        DriveController.driveCartesian(0.25, 0, 0);
    }

    //Testing Method For Backward and Calibration
    public void TestBackward()
    {   
        DriveController.driveCartesian(-0.25, 0, 0);
    }

    public void TestLeft()
    {
        DriveController.driveCartesian(0, 0.25, 0);
    }

    public void TestRight()
    {
        DriveController.driveCartesian(0, -0.25, 0);
    }

    public void TestStop()
    {
        DriveController.driveCartesian(0, 0, 0);
    }
}