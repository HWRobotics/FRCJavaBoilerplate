/**
 * 
 */
package org.usfirst.frc1148.modules;
import org.usfirst.frc1148.interfaces.RobotModule;
import org.usfirst.frc1148.systems.Talon;
import edu.wpi.first.wpilibj.DigitalInput;

public class DrawbridgeModule implements RobotModule {

	private DigitalInput switchClosed;
	private DigitalInput switchOpen;
	boolean enabled = false;
	private Talon motorTalon;
	
	//debug 
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
	
	public void Enable(){
		enabled = true;
	}
	
	public void Disable(){
		enabled = false;
	}
	
	public void Open(){
		targetState = true;
	}
	public void Close(){
		targetState = false;
	}

	public void updateTick(int mode) {
		//open is normally off
		//closed is normally on
		if(switchOpen.get() != currOpen){
			System.out.println("'Open' Switch Change = "+ switchOpen.get());
			currOpen = !currOpen;
		}
		if(switchClosed.get() != currClosed){
			System.out.println("'Close' Switch Change = "+ switchClosed.get());
			currClosed = !currClosed;
		}
		
		if(!enabled){
			motorTalon.set(0);
			return;
		}
		if(targetState){
			if(!switchOpen.get()){
				motorTalon.set(-.3);
			}else{
				motorTalon.set(0);
			}
		}else{
			if(switchClosed.get()){
				motorTalon.set(.3);
			}else{
				motorTalon.set(0);
			}
		}
	}

}
