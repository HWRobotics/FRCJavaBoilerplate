package org.usfirst.frc1148.modules;

import org.usfirst.frc1148.data.MoveData;
import org.usfirst.frc1148.interfaces.RobotModule;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutoDriveModule implements RobotModule {

    DigitalInput rangefinderMid;
    DigitalInput rangefinderClose;
    int targetDegrees = 0;
    boolean autoOrient = false;
    boolean approachWall = false;
    RobotDriver driver;

    public AutoDriveModule(RobotDriver driver) {
        this.driver = driver;
    }

    public void initModule() {
        rangefinderMid = new DigitalInput(2);
        rangefinderClose = new DigitalInput(3);
    }

    public void activateModule() {
        Disable();

    }

    public void deactivateModule() {
    }

    public AutoDriveModule Disable() {
        MoveData data = driver.getMoveData();
        if (autoOrient) {
            data.rotationSpeed = 0;
        }
        if (approachWall) {
            data.speed = 0;
        }
        autoOrient = approachWall = false;
        return this;
    }

    public AutoDriveModule OrientTo(int degrees) {
        autoOrient = true;
        targetDegrees = degrees;
        return this;
    }

    public AutoDriveModule ApproachWall() {
        approachWall = true;
        return this;
    }

    public void updateTick(int mode) {
        MoveData data = driver.getMoveData();

        if (autoOrient) {
            double angle = driver.getGyroAngleInRange();
            double diff = Math.abs(hdgDiff(angle, targetDegrees));
            double speed;
            if (diff > 1) {
                if (diff < 45) {
                    speed = Math.abs(diff / 45);
                    speed = speed * speed;
                } else if (diff < 100) {
                    speed = 0.4;
                } else {
                    speed = 0.8;
                }
                if (isTurnCCW(angle, targetDegrees)) {
                    speed *= -1;
                }
                data.rotationSpeed = speed;
            } else {
                data.rotationSpeed = 0;
            }
        }
        //approachWall = true; //VERY F**ING IMPORTANT TO CHANGE THIS
        //if(approachWall){
        //System.out.println("APPROACH WALL: MID: "+rangefinderMid.get()+" CLOSE: "+rangefinderClose.get());
        //}

    }

    double hdgDiff(double h1, double h2) { // angle between two headings
        double diff = (h1 - h2 + 3600 % 360);
        return diff <= 180 ? diff : 360 - diff;
    }

    boolean isTurnCCW(double hdg, double newHdg) { // should a new heading turn left ie. CCW?
        double diff = newHdg - hdg;        // CCW = counter-clockwise ie. left
        return diff > 0 ? diff > 180 : diff >= -180;
    }
}
