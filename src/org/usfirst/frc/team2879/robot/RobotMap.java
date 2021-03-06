
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
	public static int frontleftmotor = 0;
	public static int rearleftmotor = 1;
	public static int frontrightmotor = 2;
	public static int rearrightmotor = 3;
		
	public static int joystickport = 0;

	public static int lift = 6;
	public static int topIntakeLeft = 3;
	public static int topIntakeright = 2;
	public static int bottomIntakeright = 0;
	public static int bottomIntakeleft = 1;

	//constants for the encoders
	public static double distancePerPulse=1;
	public static double minRate=1;
	public static double maxRate=1;
	
	//other constants
	public static double neutralspeed;
	public static int timeoutMs = 0;

	public static int distancePerRevolution=1;
}
