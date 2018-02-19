package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class lift extends Command {
	double speed;

	/**
	 * this command makes the redline spin
	 * 
	 * @param speed
	 *            the speed of the lift
	 */
	public lift(double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.lift);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		speed = Robot.oi.GetJoystick().getThrottle();
		Robot.lift.set(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.lift.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
