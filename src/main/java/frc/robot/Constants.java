/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
  public final class HookMechanismConstants{
    public static final int xboxPort = 12345;
    public static final int pistonForwardChannel = 1;
    pulbic static final int pistonReverseChannel = 1;
  }
  public final class IntakeConstants{
    public static final int forwardChannel =1;
    public static final int reverseChannel = 2;
    public static final int xboxPort = 0;
    public static final int victorPort = 78;
    
    
  }
  public final class IntakeDropConstants{
    public static final int intakeLiftPortForwardChannel = 1;
    public static final int intakeLiftPortReverseChannel = 2;
  
  }
}
