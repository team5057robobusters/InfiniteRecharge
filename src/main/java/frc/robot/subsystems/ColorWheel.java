/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Add your docs here.
 */
public class ColorWheel {
    private Timer time;
    private Victor colorMotor;

    int colorMotorPort = 8;

    public ColorWheel()
    {
        time = new Timer();
        colorMotor = new Victor(colorMotorPort);
    }
    
    public void spinWheel(int numberOfSpins)
    {
        time.start();
        colorMotor.set(0.5);
        double getTime = calculateTime(numberOfSpins);
        if (time.get() == getTime)
        {
            colorMotor.set(0);
            time.stop();
            time.reset();
        }
    }

    private double calculateTime(int numberOfSpins)
    {
        double returnTime = 0;
        //figure this out once we figure out how to convert number of spins to a time
        return returnTime;
    }

    public void spinUntilColor(Color selectedColor, Color colorFromSensor)
    {
        colorMotor.set(0.5);
        if (selectedColor.equals(colorFromSensor))
        {
            colorMotor.set(0);
        }
    }
}
