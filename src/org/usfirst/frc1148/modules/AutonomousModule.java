package org.usfirst.frc1148.modules;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc1148.data.MoveData;
import org.usfirst.frc1148.interfaces.RobotModule;

public class AutonomousModule implements RobotModule {

    RobotDriver driver;
    DrawbridgeModule drawbridge;
    int state = 0;
    Timer timer;
    Timer secondTimer;
    int wiggle = 0;
    boolean autonomousEnabled = true;
    AutoDriveModule autoDrive;

    public AutonomousModule(RobotDriver drive, DrawbridgeModule box, AutoDriveModule autoDrive) {
        driver = drive;
        drawbridge = box;
        this.autoDrive = autoDrive;
    }

    public void initModule() {
        timer = new Timer();
        secondTimer = new Timer();

    }

    public void activateModule() {
        //drawbridge.Close();
        //	driver.Relative();
    }
//

    public void deactivateModule() {
    }

    public void updateTick(int mode) {
        if (mode == 1 && autonomousEnabled) {
            MoveData moveData = driver.getMoveData();
            drawbridge.Enable();
            driver.Relative();
            switch (state) {
                case 0: //startup 0 seconds
                    timer.reset();
                    timer.start();
                    state++;
                    break;
                case 1://move right 2.5 seconds
                    moveData.angle = 90;
                    moveData.speed = 0.5;
                    autoDrive.OrientTo(0);
                    if (timer.get() > 2.5) {
                        timer.stop();
                        timer.reset();
                        timer.start();
                        state++;
                    }
                    break;
                case 2: //2 seconds forward
                    moveData.angle = 0;
                    moveData.speed = 0.6;
                    if (timer.get() > 2) {
                        timer.stop();
                        timer.reset();
                        timer.start();
                        state++;
                    }
                    break;
                case 3: //turn to 45
                    autoDrive.OrientTo(360 - 45);
                    if (timer.get() > 0.5) {
                        timer.stop();
                        timer.reset();
                        timer.start();
                        state++;
                    }
                    break;
                case 4: // move forward 1.8 second
                    autoDrive.OrientTo(360 - 45);
                    moveData.speed = 0.5;
                    moveData.angle = 0;
                    if (timer.get() > 1.8) {
                        timer.stop();
                        timer.reset();
                        timer.start();
                        state++;
                    }
                    break;
                case 5: // move right 1 second
                    autoDrive.OrientTo(360 - 45);
                    moveData.speed = 0.3;
                    moveData.angle = 45;
                    if (timer.get() > 1) {
                        timer.stop();
                        timer.reset();
                        timer.start();
                        state++;
                    }
                    break;
                case 6: //open up! 3 seconds
                    moveData.speed = 0.05;
                    moveData.angle = 45;
                    autoDrive.Disable();
                    moveData.rotationSpeed = 0;
                    drawbridge.Open();
                    if (timer.get() > 3) {
                        timer.stop();
                        timer.reset();
                        timer.start();
                        drawbridge.Close();
                        state++;
                    }
                    break;/*
                case 7://move away from wall, 1 second
                    moveData.speed = 0.8;
                    moveData.angle = 45 + 180;
                    autoDrive.OrientTo(360-45);
                    if (timer.get() > 1) {
                        timer.stop();
                        timer.reset();
                        timer.start();
                        state++;
                    }
                    break;
                case 8: //3 seconds back!
                    moveData.speed = 0.8;
                    moveData.angle = 180;
                    autoDrive.OrientTo(180);
                    if (timer.get() >= 2.9) {
                        timer.stop();
                        timer.reset();
                        timer.start();
                        state++;
                    }*/
                    //break;
                default:
                    autoDrive.Disable();
                    moveData.speed = 0;
                    moveData.angle = 0;
                    driver.Relative();
                    break;
            }
        }
        /*
         if (mode == 1 && autonomousEnabled) { //This should be exactly 15 seconds total.
         MoveData moveData = driver.getMoveData();
         drawbridge.Enable();
         //driver.NotRelative();
         switch (state) {
         case 0: //Startup 0 seconds
         timer.reset();
         timer.start();
         state++;
         System.out.println("Moving to state " + state);
         break;
         case 1: //Move right 3 seconds
         moveData.angle = 90;
         moveData.speed = .8;
         autoDrive.OrientTo(0);
         drawbridge.Close();
         if (timer.get() > 3) {
         timer.stop();
         timer.reset();
         timer.start();
         state++;
         System.out.println("Moving to state " + state);
         }
         break;
         case 2: //Move forward 3 seconds
         //drawbridge.Open();
         moveData.angle = 0;
         moveData.speed = 0.8;
         autoDrive.OrientTo(0);
                    
         if(timer.get()>3){
         timer.stop();
         timer.reset();
         timer.start();
         state++;
         System.out.println("Moving to state " + state);
         }
         break;
         case 3: //angle correctly 1 second
         moveData.speed = 0;
         moveData.angle = 90;
         autoDrive.OrientTo(360-45);
         if (timer.get() > 1) {
         state++;
         System.out.println("Moving to state " + state);
         timer.stop();
         timer.reset();
         timer.start();
         }
         break;
         case 4: // move toward goal 3 sec
         moveData.angle = 45;
         moveData.speed = 0.5;
         if (timer.get() > 3) {
         timer.stop();
         timer.reset();
         timer.start();
         state++;
         System.out.println("Moving to state " + state);
         }
         break;
         case 5: //dump 3 sec
         moveData.angle = 0;
         moveData.speed = 0;
         //drawbridge.Open();
         if(timer.get() > 3){
         timer.stop();
         timer.reset();
         timer.start();
         state++;
         System.out.println("Moving to state " + state);
         }
         break;
         case 6: // move away 2 sec --> autoorient 0 stop
         moveData.angle = 360-45;
         moveData.speed = 1;
         drawbridge.Close();
         autoDrive.OrientTo(360-45);
         if(timer.get() > 2){
         moveData.speed = 0;
         autoDrive.OrientTo(0);
         moveData.angle = 0;
         state++;
         }
         default:
         driver.Relative();
         System.out.println("Autonomous finished.");
         break;
         }
         }
         * */
    }
}
