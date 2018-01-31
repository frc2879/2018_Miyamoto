
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
	
	// motor ports
	public static int frontleftmotor = 2;
	public static int rearleftmotor = 1;
	public static int frontrightmotor = 3;
	public static int rearrightmotor = 0;
	
	
	public static int joystickport = 0;

	// lift 15
	public static int topIntakeLeft = 10;
	public static int topIntakeright = 11;
	public static int bottomIntakeright = 12;
	public static int bottomIntakeleft = 14;
}
