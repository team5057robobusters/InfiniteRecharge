/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.pixy2.PIDLoop;
import io.github.pseudoresonance.pixy2api.*;

//README: For those who are wondering at what numBlocks are
//in the previous C++ code, Roba has discovered it is 
//the same as numBlockspixy.getCCC().getBlocks().size().
//Your welcome.

public class Vision extends SubsystemBase {

    private Pixy2 pixy;
    private int width;
    private int height;
    private short index; //short is the java version of int16_t
    public double x1;

    public Vision()
    {
        System.out.println("I am here");
        pixy = Pixy2.createInstance(new SPILink());
        pixy.init();
        index -= 1;
        System.out.println("Pixy initialized successfully!");
        pixy.changeProg("color_connected_components".toCharArray());

    }

    public Pixy2 getPixy(){
        return pixy;
    }

    // Take the blockNum-th block (blocks[blockNum]) that's been around for at least 30 frames (1/2 second)
    // and return its index, otherwise return -1
    private short acquireBlock(int blockNum)
    {
        if (pixy.getCCC().getBlocks().size() > blockNum && pixy.getCCC().getBlocks().get(blockNum).getAge()>30)
            return (short)pixy.getCCC().getBlocks().get(blockNum).getIndex();

        else return -1;
    }

    private Block trackBlock(short ind)
    {
        byte i;

        for (i=0; i<pixy.getCCC().getBlocks().size(); i++)
        {
            if (ind==pixy.getCCC().getBlocks().get(i).getIndex())
            return pixy.getCCC().getBlocks().get(i);
        }
        return null;
    }

    public void trackOrangeBall() {
        // use ccc program to track objects
        Block block = null;    
        
        pixy.getCCC().getBlocks(false, 1, 1);
        if (index == -1){
            index = acquireBlock(0);
        }
        // If we've found a block, find it, track it
        if (index>=0)
          block = trackBlock(index);
        
        if (block != null)
        {       
          block.print();

          width = block.getWidth();
          height = block.getHeight();
        }
        else // no object detected, go into reset state
        {
          index = -1;
          width = -1; //int cannot be NULL in java
          height = -1; //int cannot be NULL in java
        }
    }
    
    public void trackVisionTarget() {
      // use ccc program to track objects
      //two pieces of tape to track
      Block block1 = null;
      
      pixy.getCCC().getBlocks(false, 1, 1);
      if (index == -1){
        index = acquireBlock(0);
      }
      // If we've found the first block, find it, track it
      if (index>=0){
        block1 = trackBlock(index);
        
      }
            
      if (block1 != null) // && block2 != null)
      {   
        
        width = block1.getWidth();
        height = block1.getHeight();
        x1 = block1.getX();
        SmartDashboard.putNumber("Color", block1.getSignature());

        SmartDashboard.putNumber("Target 1 X", block1.getX());
        SmartDashboard.putNumber("Target 1 Y", block1.getY());
        SmartDashboard.putNumber("Target 1 Box Width", block1.getWidth());
        SmartDashboard.putNumber("Target 1 Angle", block1.getAngle());
        SmartDashboard.putNumber("Target 1 Box Height",block1.getHeight());
        SmartDashboard.putNumber("Signature",block1.getSignature());
      }
      else // no object detected, go into reset state
      {

        index = -1;
        width = -1; //int cannot be NULL in java
        height = -1;  //int cannot be NULL in java
      }
    }
     
    //width of target in pixels
    public int returnWidthValue()
    {
      return width;
    }
    
    //height of target in pixels
    public int returnHeightValue()
    {
      return height;
    }
}