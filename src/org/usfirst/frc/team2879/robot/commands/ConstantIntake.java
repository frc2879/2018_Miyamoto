package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ConstantIntake extends Command {

	double speed;
	boolean high;
	double fastspeed;

	public ConstantIntake(double speed, boolean high) {
		this(speed, speed, high);
	}

	public ConstantIntake(double speed, double fastspeed, boolean high) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.speed = speed;
		this.high = high;
		this.fastspeed = fastspeed;
		if (high) {
			requires(Robot.cubeIntakeHigh);
		} else {
			requires(Robot.cubeIntakeLow);
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// speed=Robot.oi.GetJoystick().getThrottle();
		// SmartDashboard.putNumber("throttle", speed);
		if (high) {
			Robot.cubeIntakeHigh.go(speed);
			if (Robot.cubeIntakeHigh.getLimitswitch().get()) {
				Robot.cubeIntakeHigh.go(fastspeed);
			}
		} else {
			Robot.cubeIntakeLow.go(speed);
			if (Robot.cubeIntakeLow.getLimitswitch().get()) {
				Robot.cubeIntakeLow.go(fastspeed);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
