/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import com.ctre.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;

import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command m_teleopCommand;
  private UltrasonicSensor m_ultrasonic;
  private RobotContainer m_robotContainer;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private Vision m_pixy2;
  private HookMechanism m_hook;
  //ports
  final int leftFrontCanPort = 4;
  final int rightFrontCanPort = 5;
  final int leftRearCanPort = 3;
  final int rightRearCanPort = 2;

  final int xboxPort = 0;

  //Consts
  final double throttlevalue = 1;//[0 - 1]

  //Define Drive Motors
  WPI_TalonSRX LeftFrontMotor;
  WPI_TalonSRX RightFrontMotor;
  WPI_TalonSRX LeftRearMotor;
  WPI_TalonSRX RightRearMotor;

  //Define FRCSpeedController Groups
  /*SpeedControllerGroup LeftFrontDrive;
  SpeedControllerGroup RightFrontDrive;
  SpeedControllerGroup LeftRearDrive;
  SpeedControllerGroup RightRearDrive;
  */

  MecanumDrive DriveController;

  XboxController xboxControl;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    LeftFrontMotor = new WPI_TalonSRX(leftFrontCanPort);
    RightFrontMotor = new WPI_TalonSRX(rightFrontCanPort);
    LeftRearMotor = new WPI_TalonSRX(leftRearCanPort);
    RightRearMotor = new WPI_TalonSRX(rightRearCanPort);

    //LeftFrontDrive = new SpeedControllerGroup(speedController, speedControllers)
    
    m_ultrasonic = new UltrasonicSensor(0);
    m_pixy2 = new Vision();
    m_hook = new HookMechanism();

    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    Color detectedColor = m_colorSensor.getColor();
    double distance = m_ultrasonic.getInches();
    SmartDashboard.putNumber("Distance:", distance); 
    m_pixy2.trackVisionTarget();
    

    /**
     * The sensor returns a raw IR value of the infrared light detected.
     */
    double IR = m_colorSensor.getIR();

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the
     * sensor.
     */
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("IR", IR);

    int proximity = m_colorSensor.getProximity();

    SmartDashboard.putNumber("Proximity", proximity);
    
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {

    m_teleopCommand = m_robotContainer.getTeleopCommand();
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(m_teleopCommand != null)
    {
      m_teleopCommand.execute();
    }
    if(xboxControl.getAButton()){
      if(m_pixy2.x1<150){
        DriveController.driveCartesian(0, 0, .5);
      }
      if(m_pixy2.x1>170){
        DriveController.driveCartesian(0, 0, -.5);
      }
      
    }
    m_hook.checkButton();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
