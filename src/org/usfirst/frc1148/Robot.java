/*
 * ===========
 * Robot Code File
 * Team 1148 Framework
 * ===========
 * Author: Christian Stewart
 * Date: 2/11/2014
 */

package org.usfirst.frc1148;

//Core JAVA stuff
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import java.util.Enumeration;
import java.util.Hashtable;

//Robot modules
import org.usfirst.frc1148.interfaces.RobotModule;
import org.usfirst.frc1148.modules.AutoDriveModule;
import org.usfirst.frc1148.modules.AutonomousModule;
import org.usfirst.frc1148.modules.JoyStickInputModule;
import org.usfirst.frc1148.modules.RobotDriver;

public class Robot extends IterativeRobot {
    
    //Stores your robot code modules.
    private Hashtable modules;

    public Robot() {
        Watchdog.getInstance().feed();
        System.out.println("Constructing Robot...");
        modules = new Hashtable();

        /*
         * Here you need to register all of your code modules.
         * A code module is essentially a "feature" of the robot.
         * It is passed an instance of this so that it can get
         * references to other modules.
         */
        System.out.println("Constructing modules...");
        modules.put("robotDriver", new RobotDriver(this));
        modules.put("autodrive", new AutoDriveModule(this));
        modules.put("joystick", new JoyStickInputModule(this));
        modules.put("autonomous", new AutonomousModule(this));
        
        System.out.println("Robot construction done...");
        
        Watchdog.getInstance().feed();
    }
    
    /*
     * ======== ROBOT FRAMEWORK ===========
     * These are accessable within your robot modules.
     * Use them to get references to other modules etc.
     * ====================================
     */
    
    public RobotModule GetModuleByName(String name)
    {
        return (RobotModule)modules.get(name);
    }
    
    /*
     * ========= FRC Callbacks ============
     * It is not recommended to edit below here.
     * You should instead create a robot module and register it.
     * ====================================
     */
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        Watchdog.getInstance().feed();
        System.out.println("Initializing Robot...");

        Enumeration e = modules.elements();
        RobotModule value;
        while (e.hasMoreElements()) {
            value = (RobotModule) e.nextElement();
            value.initModule();
        }
        System.out.println("Robot initialization done...");
    }

    /**
     * Init the autonomous mode.
     */
    public void autonomousInit() {
        Watchdog.getInstance().feed();
        Enumeration e = modules.elements();
        RobotModule value;
        while (e.hasMoreElements()) {
            value = (RobotModule) e.nextElement();
            value.activateModule();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Watchdog.getInstance().feed();
        Enumeration e = modules.elements();
        RobotModule value;
        while (e.hasMoreElements()) {
            value = (RobotModule) e.nextElement();
            value.updateTick(1);
        }
    }

    public void disabledInit() {
        Watchdog.getInstance().feed();
        Enumeration e = modules.elements();
        RobotModule value;
        while (e.hasMoreElements()) {
            value = (RobotModule) e.nextElement();
            value.deactivateModule();
        }
    }

    public void disabledPeriodic() {
        Watchdog.getInstance().feed();
    }

    public void teleopInit() {
        Watchdog.getInstance().feed();
        Enumeration e = modules.elements();
        RobotModule value;
        while (e.hasMoreElements()) {
            value = (RobotModule) e.nextElement();
            value.activateModule();
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Watchdog.getInstance().feed();

        Enumeration e = modules.elements();
        RobotModule value;
        while (e.hasMoreElements()) {
            value = (RobotModule) e.nextElement();
            value.updateTick(2);
            Watchdog.getInstance().feed();
        }
        Watchdog.getInstance().feed();
    }

    public void disabledTeleop() {
        Watchdog.getInstance().feed();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        Watchdog.getInstance().feed();

        Enumeration e = modules.elements();
        RobotModule value;
        while (e.hasMoreElements()) {
            value = (RobotModule) e.nextElement();
            value.updateTick(3);
            Watchdog.getInstance().feed();
        }
    }

    public void disabledTest() {
        Watchdog.getInstance().feed();
    }
}
