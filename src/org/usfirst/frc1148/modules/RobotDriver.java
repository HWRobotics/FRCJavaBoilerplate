package org.usfirst.frc1148.modules;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import org.usfirst.frc1148.Robot;
import org.usfirst.frc1148.data.MoveData;
import org.usfirst.frc1148.interfaces.RobotModule;

public class RobotDriver implements RobotModule {

    Robot robot;
    Talon frontLeft;
    Talon backLeft;
    Talon frontRight;
    Talon backRight;
    public Gyro robotGyro;
    boolean relativeDrive = true;
    int angleOffset = 0;
    MoveData moveData;
    boolean enableMotors = true;

    public RobotDriver(Robot robot) {
        this.robot = robot;
        moveData = new MoveData();
    }
    /*
     * Set the move data for the controller
     */

    public void setMoveData(MoveData data) {
        moveData = data;
    }

    public void initModule() {
        System.out.println("Initialzing robot driver module!");
        frontLeft = new Talon(2);
        frontRight = new Talon(4);
        robotGyro = new Gyro(2);
        backLeft = new Talon(1);
        backRight = new Talon(3);
        System.out.println("Robot driver module initialized.");
    }

    public void activateModule() {
        System.out.println("Robot driver module activated!");
        moveData.angle = 0;
        moveData.speed = 0;
        //robotGyro.reset();
    }

    public void resetGyro() {
        angleOffset = 0;
        robotGyro.reset();
    }

    public void deactivateModule() {
        System.out.println("Robot driver module deactivated!");
        frontLeft.set(0);
        frontRight.set(0);
        backLeft.set(0);
        backRight.set(0);
    }

    public RobotDriver ToggleRelative() {
        relativeDrive = !relativeDrive;
        return this;
    }

    public RobotDriver Relative() {
        relativeDrive = true;
        return this;
    }

    public RobotDriver NotRelative() {
        relativeDrive = false;
        return this;
    }

    public void processMovementVector() {
        double speed = moveData.speed;
        double moveAngle = moveData.angle;

        if (relativeDrive) {
            moveAngle -= robotGyro.getAngle() + angleOffset;
        }
        
        while (moveAngle < 0 || moveAngle > 360) {
            if (moveAngle < 0) {
                moveAngle += 360;
            } else if (moveAngle > 360) {
                moveAngle -= 360;
            }
        }
        
        moveAngle = moveAngle / 180 * Math.PI;
        double rotSpeed = moveData.rotationSpeed;

        double frontLefts = speed *4/3* Math.sin(moveAngle + (Math.PI / 4)) + rotSpeed;
        double frontRights = (speed*4/3 * Math.cos(moveAngle + (Math.PI / 4)) - rotSpeed);
        double backLefts = speed *4/3* Math.cos(moveAngle + (Math.PI / 4)) + rotSpeed;
        double backRights = (speed *4/3* Math.sin(moveAngle + (Math.PI / 4)) - rotSpeed);
        
        double[] values = new double[]{frontLefts, frontRights, backLefts, backRights};
        double max = Math.abs(values[0]);
        for (int i = 1; i < 4; i++) {
            if (Math.abs(values[i]) > max) {
                max = Math.abs(values[i]);
            }
        }
        if (max > 1) {
            frontLefts /= max;
            frontRights /= max;
            backLefts /= max;
            backRights /= max;
        }

        //apply motor movement
        if (enableMotors) {
            frontLeft.set(frontLefts);
            frontRight.set(-1*frontRights);
            backLeft.set(backLefts);
            backRight.set(-1*backRights);
        } else {
            System.out.println("FL: "+frontLefts+" FR: "+frontRights+" BL: "+backLefts+" BR: "+backRights);
            System.out.println("IS: "+moveData.speed+" ANG: "+moveData.angle + " ROT: "+moveData.rotationSpeed);
        }
    }

    public void updateTick(int mode) {
        processMovementVector();

    }

    public MoveData getMoveData() {
        return moveData;
    }

    public double getGyroAngle() {
        return robotGyro.getAngle();
    }

    public double getGyroAngleInRange() {
        double angle = getGyroAngle();
        while (angle < 0 || angle > 360) {
            if (angle < 0) {
                angle += 360;
            } else if (angle > 360) {
                angle -= 360;
            }
        }
        return angle;

    }
}
