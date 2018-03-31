package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoverSlam extends Command {
	private double speed;
	private int timer;

	/*
	 * speed is negative to go left
	 */
	public HoverSlam(double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer=0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.drive(speed, 0, 0);
		timer++;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (timer > 50);
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
