/**
 *
 */
package org.usfirst.frc1148.modules;

import edu.wpi.first.wpilibj.DigitalInput;
import org.usfirst.frc1148.interfaces.RobotModule;
import org.usfirst.frc1148.systems.Talon;

public class DrawbridgeModule implements RobotModule {

    private DigitalInput switchClosed;
    private DigitalInput switchOpen;
    boolean enabled = false;
    private Talon motorTalon;
    //debug 
    private boolean halfwayOpen = false;
    private boolean currClosed = false;
    private boolean currOpen = false;
    //True if open
    private boolean targetState = false;

    public void initModule() {
        switchClosed = new DigitalInput(7);
        switchOpen = new DigitalInput(6);
        motorTalon = new Talon(5);
    }

    public void activateModule() {
        Enable();

    }

    public void deactivateModule() {
        enabled = false;

    }

    public void Enable() {
        enabled = true;
    }

    public void Disable() {
        enabled = false;
    }

    public void Open() {
        targetState = true;
        halfwayOpen = false;
    }

    public void Close() {
        targetState = false;
        halfwayOpen = false;
    }

    public void Half() {
        halfwayOpen = true;
    }

    public void updateTick(int mode) {
        //open is normally off
        //closed is normally on
        if (switchOpen.get() != currOpen) {
            System.out.println("'Open' Switch Change = " + switchOpen.get());
            currOpen = !currOpen;
        }
        if (switchClosed.get() != currClosed) {
            System.out.println("'Close' Switch Change = " + switchClosed.get());
            currClosed = !currClosed;
        }

        if (!enabled) {
            motorTalon.set(0);
            return;
        }
        if (halfwayOpen) {
            if (switchOpen.get()) {
                motorTalon.set(0.3);
            } else if (!switchClosed.get()) {
                motorTalon.set(-0.3);
            } else {
                motorTalon.set(0);
            }
        } else if (targetState) {
            if (!switchOpen.get()) {
                motorTalon.set(-.3);
            } else {
                motorTalon.set(0);
            }
        } else {
            if (switchClosed.get()) {
                motorTalon.set(.2);
            } else {
                motorTalon.set(0);
            }
        }
    }
}
