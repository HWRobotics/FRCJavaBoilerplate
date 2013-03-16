package org.usfirst.frc1148.systems;

import edu.wpi.first.wpilibj.Jaguar;

public class Talon extends Jaguar {
	boolean reverse=  false;
	public Talon(int x){
		super(x);
		this.setBounds(210, 123, 123, 123, 50);
	
	}
	public void set(double speed){
		if(reverse)
			speed = -speed;
		super.set(speed+0.07874);
	}
	public Talon Reverse(){
		reverse = !reverse;
		return this;
	}
}
