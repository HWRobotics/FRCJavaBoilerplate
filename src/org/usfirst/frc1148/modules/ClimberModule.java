package org.usfirst.frc1148.modules;

import org.usfirst.frc1148.interfaces.RobotModule;
import org.usfirst.frc1148.systems.Talon;

public class ClimberModule implements RobotModule {

	private Talon arm;
	private boolean whichWinch = true;
	private Talon winch;
	private Talon winch2;
	public void initModule() {
		arm = new Talon(6);
		winch = new Talon(7);
		winch2 = new Talon(8);
		
	}

	
	public void activateModule() {
		// TODO Auto-generated method stub
		arm.set(0);
		winch.set(0);
		winch2.set(0);
	}

	public void SetFirstWinch(){
		whichWinch = true;
	}
	public void SetSecondWinch(){
		whichWinch = false;
	}
	public void deactivateModule() {
		
	}

	public ClimberModule SetWinchSpeed(double speed){
		if(whichWinch)
			winch.set(-speed);
		else
			winch2.set(-speed);
		if(!whichWinch){
			winch.set(0);
		}else{
			winch2.set(0);
		}
		return this;
	}
	  
	public ClimberModule SetArmSpeed(double speed){
		arm.set(speed);
		return this;
	}
	
	public void Stop(){
		winch.set(0);
		arm.set(0);
	}
	public void updateTick(int mode) {
		if(mode != 2)
		{
			arm.set(0);
			winch.set(0);
		}
	}

}
