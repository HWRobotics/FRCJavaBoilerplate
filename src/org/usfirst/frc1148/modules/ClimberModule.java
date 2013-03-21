package org.usfirst.frc1148.modules;

import edu.wpi.first.wpilibj.Talon;
import org.usfirst.frc1148.interfaces.RobotModule;

public class ClimberModule implements RobotModule {

    private Talon arm;
    private Talon winch;
    private Talon boxLift;

    public void initModule() {
        arm = new Talon(6);
        winch = new Talon(7);
        boxLift = new Talon(8);
    }

    public void activateModule() {
        // TODO Auto-generated method stub
        arm.set(0);
        winch.set(0);
        boxLift.set(0);
    }

    public void deactivateModule() {
    }

    public ClimberModule SetWinchSpeed(double speed) {
        winch.set(-speed);
        return this;
    }

    public ClimberModule SetArmSpeed(double speed) {
        arm.set(speed);
        return this;
    }
    
    public ClimberModule LiftBox(){
        boxLift.set(0.2);
        return this;
    }
    
    public ClimberModule LowerBox(){
        boxLift.set(-0.1);
        return this;
    }
    
    public ClimberModule StopBox(){
        boxLift.set(0);
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
