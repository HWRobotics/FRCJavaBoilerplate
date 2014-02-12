package org.usfirst.frc1148.modules;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc1148.Robot;
import org.usfirst.frc1148.data.MoveData;
import org.usfirst.frc1148.interfaces.RobotModule;

public class AutonomousModule implements RobotModule {

    RobotDriver driver;
    int state = 0;
    Timer timer;
    Timer secondTimer;
    int wiggle = 0;
    boolean autonomousEnabled = true;
    AutoDriveModule autoDrive;
    Robot robot;

    public AutonomousModule(Robot robot) {
        this.robot = robot;
    }

    public void initModule() {
        timer = new Timer();
        secondTimer = new Timer();
        autoDrive = (AutoDriveModule)robot.GetModuleByName("autoDrive");
    }

    public void activateModule() {
    }

    public void deactivateModule() {
    }

    //For autonomous we just do timed states
    //Each state incriments to the next state (timed)
    //This way we can be sure of the timing of autonomous
    public void updateTick(int mode) {
        if (mode == 1 && autonomousEnabled) {
            MoveData moveData = driver.getMoveData();
            driver.Relative();
            switch (state) {
                case 0: //startup 0 seconds
                    timer.reset();
                    timer.start();
                    state++;
                    break;
                default: //If it finishes or there is no next state (failsafe)
                    autoDrive.Disable();
                    moveData.speed = 0;
                    moveData.angle = 0;
                    driver.Relative();
                    break;
            }
        }
    }
}
