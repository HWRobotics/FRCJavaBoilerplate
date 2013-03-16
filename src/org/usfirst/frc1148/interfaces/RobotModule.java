package org.usfirst.frc1148.interfaces;

import org.usfirst.frc1148.HWRobot;

/**
*
* @author HW Robotics
*/
public interface RobotModule {
   /**
    * Initialize the module.
    */
   public void initModule();
   
   public void activateModule();
   
   public void deactivateModule();
  
   public void updateTick(int mode);
}
