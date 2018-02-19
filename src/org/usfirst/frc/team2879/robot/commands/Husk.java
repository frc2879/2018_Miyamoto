package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * NEVER CALL THIS!! it will kill the cube and motors!
 */
public class Husk extends Command {
	int time = 0;

	public Husk() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cubeIntakeHigh);
		requires(Robot.cubeIntakeLow);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// this will toggle moters: in for 40 ms, out for 20.

		if (time < 4) {
			Robot.cubeIntakeHigh.go(1);
			Robot.cubeIntakeLow.go(1);
			time++;
		} else if (time < 6) {
			Robot.cubeIntakeHigh.go(-1);
			Robot.cubeIntakeLow.go(-1);
			time++;
		} else {
			time = 0;
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
