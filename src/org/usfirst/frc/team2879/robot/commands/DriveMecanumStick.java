package org.usfirst.frc.team2879.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2879.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveMecanumStick extends Command {
	private double speed;
	
	public DriveMecanumStick(double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		this.speed=speed;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        //Robot.drivetrain.drive(0,.1,0);
		Robot.drivetrain.drive(Robot.oi.GetJoystick(),speed);
		SmartDashboard.putNumber("x value", Robot.drivetrain.getNavX().getDisplacementX());
		SmartDashboard.putNumber("y value", Robot.drivetrain.getNavX().getDisplacementY());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
        Robot.drivetrain.drive(0,0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
