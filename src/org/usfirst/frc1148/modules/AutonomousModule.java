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
    }
}
