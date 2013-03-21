/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc1148.systems;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author HW Robotics
 */
public class TalonReversable extends Talon {
    boolean reverse = false;
    public TalonReversable(int port){
        super(port);
    }
    public void set(double speed){
        super.set((reverse ? -speed : speed));
    }
    public TalonReversable Reverse(){
        reverse = true;
        return this;
    }
}
