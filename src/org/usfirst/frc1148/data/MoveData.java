package org.usfirst.frc1148.data;

/*
 * Stores the data for movement of the robot.
 * Only 1 should exist at once.
 */
public class MoveData 
{
    public double rotationSpeed;
    public double angle;
    public double speed;

    public MoveData() {
        angle = 0;
        speed = 0;
        rotationSpeed = 0;
    }
}
