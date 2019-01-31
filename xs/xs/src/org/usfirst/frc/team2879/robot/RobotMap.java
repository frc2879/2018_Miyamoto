/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2879.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	
	
	//motor ports
	public static int frmotor = 2;
	public static int flmotor = 4;
	public static int brmotor = 8;
	public static int blmotor = 3;
	
	public static int liftmotor = 0;
	
	public static int levermotor = 0;
	
	public static int conepiston = 0;
	
	//input ports
	public static int joystickport = 0;
	
	//sensor constants
	public static double distancePerPulse = 1;
	public static double minRate = 1;
	public static double maxRate = 1;
	
	//other constants
	public static double neutralspeed;
	public static int timeoutMs = 0;
	public static int distancePerRev = 1;
	
}
