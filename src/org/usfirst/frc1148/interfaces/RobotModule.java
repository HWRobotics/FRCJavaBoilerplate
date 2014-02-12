package org.usfirst.frc1148.interfaces;

import org.usfirst.frc1148.Robot;

/**
* A module acts as a feature of the robot.
* Every robot module should derive this class.
*/
public interface RobotModule {
   /**
    * Initialize the module.
    */
   public void initModule();
   
   /*
    * Called when either mode changes or
    * the robot becomes enabled.
    */
   public void activateModule();
   
   /*
    * Called when the robot is disabled.
    */
   public void deactivateModule();
  
   /*
    * Update tick.
    *   Mode: 1=autonomous, 2=teleop, 3=test
    */
   public void updateTick(int mode);
}
