package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import PID.PID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Strafe extends Command {
	public PID rotation = new PID(.05, 0, 0);
	double magnitudeside, magnitudestraight, magnitudediagonal;

	/**
	 * this is the strafe command that maintains an angle
	 * 
	 * @param magnitudeside
	 *            the speed of sidways movement
	 * @param magnitudestraight
	 *            the speed of forward movement
	 * @param magnitudediagonal
	 *            the speed of diagonal movement
	 */
	public Strafe(double magnitudeside, double magnitudestraight, double magnitudediagonal) {
		requires(Robot.drivetrain);
		this.magnitudeside = magnitudeside;
		this.magnitudestraight = magnitudestraight;
		this.magnitudediagonal = magnitudediagonal;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		rotation.setOutputConstraints(-1, 1);
		rotation.setTarget(Robot.drivetrain.getNavX().getAngle());
		// set the acceptable error range (in degrees)
		rotation.setOkError(2);
		Robot.drivetrain.setBrakeMode(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// get the angle of the robot
		double angle = Robot.drivetrain.getNavX().getAngle();
		// calculate the output rotation rate for the PID.
		double output = rotation.calculate(angle);
		// the angle given by the mini joystick
		double pov = Robot.oi.GetJoystick().getPOV();
		// this drives the robot in the direction indicated by the POV
		// rotates on a pid controlled rotation based on navx readings.
		// the if statements determine which speed to use
		if (pov == 90 || pov == 270) {
			Robot.drivetrain.drivePolar(magnitudeside, Robot.oi.GetJoystick().getPOV(), output);
		} else if (pov == 0 || pov == 180) {
			Robot.drivetrain.drivePolar(magnitudestraight, Robot.oi.GetJoystick().getPOV(), output);
		} else {
			Robot.drivetrain.drivePolar(magnitudediagonal, Robot.oi.GetJoystick().getPOV(), output);
		}
		SmartDashboard.putNumber("angle", angle);
		SmartDashboard.putNumber("error", rotation.getError());
		SmartDashboard.putNumber("kP", rotation.kP);
		SmartDashboard.putNumber("pov", pov);
		SmartDashboard.putNumber("output", output);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.drive(0, 0, 0);
		Robot.drivetrain.setBrakeMode(false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
