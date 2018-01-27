package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import PID.PID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Strafe extends Command {
	public PID rotation = new PID(0.01,0,0);
    double magnitude,angle;
	
    public Strafe(double magnitude, double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.magnitude=magnitude;
    	this.angle=angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	rotation.setOutputConstraints(-1, 1);
    	rotation.setTarget(Robot.drivetrain.getNavX().getAngle());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drivePolar(magnitude, angle, rotation.calculate(Robot.drivetrain.getNavX().getAngle()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
