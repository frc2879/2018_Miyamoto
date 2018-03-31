package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import PID.PID;
import edu.wpi.first.wpilibj.command.Command;

/**
 * rotates a specific angle delta in degrees. clockwise is positive
 */
public class RotateByAngle extends Command {
	private PID rotatePID = new PID(.06, .2, .1);
	private double target;
	private double delta;
	private double outputRotation;

	public RotateByAngle(double deltadegrees) {
		requires(Robot.drivetrain);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.delta = deltadegrees;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		target = Robot.drivetrain.getNavX().getAngle() + delta;
		rotatePID.setTarget(target);
		// five degree error
		rotatePID.setOkError(1);
		Robot.drivetrain.setBrakeMode(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		outputRotation = rotatePID.calculate(Robot.drivetrain.getNavX().getAngle());
		if (outputRotation>.4) {
			outputRotation= .4;
		}
		if (outputRotation<-.4) {
			outputRotation= -.4;
		}
		/**
		SmartDashboard.putNumber("angle",target-Robot.drivetrain.getNavX().getAngle());
		SmartDashboard.putNumber("output", outputRotation);
		*/
		Robot.drivetrain.drive(0, 0, outputRotation);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return rotatePID.atTarget();
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
