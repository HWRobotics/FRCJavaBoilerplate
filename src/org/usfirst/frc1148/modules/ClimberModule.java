package org.usfirst.frc1148.modules;

import org.usfirst.frc1148.interfaces.RobotModule;
import org.usfirst.frc1148.systems.Talon;

public class ClimberModule implements RobotModule {

    private Talon arm;
    private Talon winch;

    public void initModule() {
        arm = new Talon(6);
        winch = new Talon(7).Reverse();
    }

    public void activateModule() {
        // TODO Auto-generated method stub
        arm.set(0);
        winch.set(0);
    }

    public void deactivateModule() {
    }

    public ClimberModule SetWinchSpeed(double speed) {
        winch.set(speed);
        return this;
    }

    public ClimberModule SetArmSpeed(double speed) {
        arm.set(speed);
        return this;
    }

    public void Stop() {
        winch.set(0);
        arm.set(0);
    }

    public void updateTick(int mode) {
        if (mode != 2) {
            arm.set(0);
            winch.set(0);
        }
    }
}
