package org.usfirst.frc1148.modules;

import org.usfirst.frc1148.data.MoveData;
import org.usfirst.frc1148.interfaces.RobotModule;

import edu.wpi.first.wpilibj.Timer;

public class AutonomousModule implements RobotModule {
	RobotDriver driver;
	DrawbridgeModule drawbridge;
	int state = 0;
	Timer timer;
	Timer secondTimer;
	int wiggle = 0;
	public AutonomousModule(RobotDriver drive, DrawbridgeModule box){
		driver = drive;
		drawbridge = box;
	}
	public void initModule() {
		timer = new Timer();
		secondTimer = new Timer();
		
	}

	public void activateModule() {
		
		//drawbridge.Close();
	//	driver.Relative();
	}
//
	public void deactivateModule() {
	}

	public void updateTick(int mode) {
		/*
		if(mode == 1){
			return;
			
			MoveData moveData = driver.getMoveData();
			drawbridge.Enable();
			driver.NotRelative();
			//ex: set angle to drive
			//moveData.angle = 45;
			//moveData.speed = 1;
			if(state == 0){
				
				timer.reset();
				timer.start();
				
				state++;
				System.out.println("Moving to state "+state);
			}else if(state==1){
				moveData.angle = 90;
				moveData.speed = .5;
				moveData.rotationSpeed = 0;
				drawbridge.Close();
				if(timer.get()>5000){
					timer.stop();
					timer.reset();
					moveData.angle = 270;
					moveData.speed = 0;
					moveData.rotationSpeed = 0;
					state++;
					System.out.println("Moving to state "+state);
				}
			}else if(state == 2){
				drawbridge.Open();
				timer.reset();
				timer.start();
				state++;
				System.out.println("Moving to state "+state);
			}else if(state == 3){
				moveData.speed = 0;
				moveData.angle = 270;
				if(timer.get() > 2000){
					state++;
					System.out.println("Moving to state "+state);
					timer.stop();
					timer.reset();
					timer.start();
				}
			}else if(state == 4){
				moveData.angle = 270;
				moveData.speed = 1;
				if(timer.get()>1000){
					timer.stop();
					timer.reset();
					state++;
					System.out.println("Moving to state "+state);
				}
			}else if(state == 5){
				moveData.angle = 0;
				moveData.speed = 0;
				//drawbridge.Close();
				moveData.rotationSpeed = 0;
				driver.Relative();
				System.out.println("Moving to state "+state);
			}
		    
		}*/
	}
	

}
