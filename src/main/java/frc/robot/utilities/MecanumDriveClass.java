package frc.robot.utilities;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class MecanumDriveClass{

    //Initialize Class Variables
    double Throttle;

    //Drive Motor Variables
    VictorSP FrontLeftMotor;
    VictorSP FrontRightMotor;
    VictorSP RearLeftMotor;
    VictorSP RearRightMotor;

    MecanumDrive DriveController;

    XboxController xboxControl;

    //Upon initilization of new MecanumDriveClass set the following variables
    public MecanumDriveClass(
        int frontLeftPort,
        int frontRightPort,
        int rearLeftPort,
        int rearRightPort,
        int xboxControllerPort,
        double throttle
    ){
        FrontLeftMotor = new VictorSP(frontLeftPort);
        FrontRightMotor = new VictorSP(frontRightPort);
        RearLeftMotor = new VictorSP(rearLeftPort);
        RearRightMotor = new VictorSP(rearRightPort);

        Throttle = throttle;
        FrontRightMotor.setInverted(true);
        RearLeftMotor.setInverted(true);

        DriveController = new MecanumDrive(FrontLeftMotor, RearLeftMotor, FrontRightMotor, RearRightMotor);
        xboxControl = new XboxController(xboxControllerPort);
    }

    //Call this method in TelopPeriodic
    public void Drive()
    {
        DriveController.driveCartesian(
            xboxControl.getY(Hand.kLeft)*Throttle
            ,-xboxControl.getX(Hand.kLeft)*Throttle
            ,-xboxControl.getX(Hand.kRight));
    }

    //Testing Method for Forward and Calibration
    public void TestForward()
    {
        DriveController.driveCartesian(
            0.5
            ,0
            ,0);
    }

    //Testing Method For Backward and Calibration
    public void TestBackward()
    {
        DriveController.driveCartesian(
            0
            ,0.5
            ,0);   
    }
}