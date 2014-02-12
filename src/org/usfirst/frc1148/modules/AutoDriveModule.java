package org.usfirst.frc1148.modules;

import org.usfirst.frc1148.Robot;
import org.usfirst.frc1148.data.MoveData;
import org.usfirst.frc1148.interfaces.RobotModule;

/*
 * ===== Team 1148 Boilerplate =====
 * This code is a freebie.
 * It essentially auto-drives - drive-by-wire.
 * It allows the operator to specify an angle to
 * orient to, and then informs the robotDriver to turn
 * to that angle.
 */
public class AutoDriveModule implements RobotModule
{
    int targetDegrees = 0;
    boolean autoOrient = false;
    RobotDriver driver;
    Robot robot;

    public AutoDriveModule(RobotDriver driver) {
        this.driver = driver;
    }

    public AutoDriveModule(Robot robot) {
        this.robot = robot;
    }

    public void initModule() 
    {
        this.driver = (RobotDriver)this.robot.GetModuleByName("robotDriver");
    }

    public void activateModule() {
        Disable(); //AKA "Reset"
    }

    public void deactivateModule()
    {
        Disable();
    }

    public AutoDriveModule Disable() {
        MoveData data = driver.getMoveData();
        if (autoOrient) {
            data.rotationSpeed = 0;
        }
        autoOrient = false;
        return this;
    }

    public AutoDriveModule OrientTo(int degrees) {
        autoOrient = true;
        targetDegrees = degrees;
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
