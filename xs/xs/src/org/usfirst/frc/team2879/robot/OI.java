/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2879.robot;

import org.usfirst.frc.team2879.robot.commands.liftMove;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	private Joystick joyboy;
	private JoystickPOVTrigger pov;
		
		public Joystick GetJoystick() {
			
			return joyboy;
			
		}
		
		public double getStickX() {
			//sets the the x axis dead zone
			double xDed = .05;
			//gets the x axis from the joy stick-a-ma-thingy
			double in = joyboy.getX();
			//square the inputs for better response
			double x = in*in;
			//actually does the dead zone thing
			if (x <= xDed) {
				x=0;
			}else {
				//when outside deadzone actually do the motor thingy
				x = (x-xDed)/(1-xDed);
			}
			//if the input was negitive amke it negitive again 
			if (in<0) {
				x=-x;
			}
			return x;
		}
		public double getStickY() {
			//sets the the y axis dead zone
			double yDed = .05;
			//gets the y axis from the joy stick-a-ma-thingy
			double in = joyboy.getY();
			//square the inputs for better response
			double y = in*in;
			//actually does the dead zone thing
			if (y <= yDed) {
				y=0;
			}else {
				//when outside deadzone actually do the motor thingy
				y = (y-yDed)/(1-yDed);
			}
			//if the input was negitive amke it negitive again 
			if (in<0) {
				y=-y;
			}
			return y;
		}
		public double getStickTwist() {
			double angleDed = .05;
			double in = joyboy.getTwist();
			double angle = in*in;
			if (angle <= angleDed) {
				angle=0;
			}else {
				angle = (angle-angleDed)/(1-angleDed);
			}
			if (in<0) {
				angle=-angle;
			}
			return angle;
		}
			
		public OI() {
			joyboy = new Joystick(RobotMap.joystickport);
			pov = new JoystickPOVTrigger(joyboy);
			//sets the commands for the buttons 
			//new JoystickButton(joyboy, 6).whileHeld(new ConstantIntake(.5,false));
			//new JoystickButton(joyboy, 5).whileHeld(new ConstantIntake(.5,true));
			//new JoystickButton(joyboy, 4).whileHeld(new ConstantIntake(-.5,false));
			//new JoystickButton(joyboy, 3).whileHeld(new ConstantIntake(-.5,true));
			//new JoystickButton(joyboy, 1).whileHeld(new StickDriveWithPID());
			
			// this is the speed of the lift. negative retracts, positive extends.
			new JoystickButton(joyboy, 11).whileHeld(new liftMove(0.20));
			new JoystickButton(joyboy, 12).whileHeld(new liftMove(-0.20));
			
			// the arguments are the speed in the different directions. (sidways speed, forward speed, diagonal speed)
			//pov.whileActive(new Strafe(.75,.25,.5));
			
		}		
		
	}


