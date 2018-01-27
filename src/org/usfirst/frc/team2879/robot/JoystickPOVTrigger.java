package org.usfirst.frc.team2879.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class JoystickPOVTrigger extends Trigger {
	private final Joystick joystick;
	
	public JoystickPOVTrigger(Joystick joystick) {
		this.joystick=joystick;
	}
	
	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return (joystick.getPOV() != -1);
	}

}
