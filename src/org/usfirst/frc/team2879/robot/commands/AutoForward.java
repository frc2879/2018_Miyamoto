package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import PID.PID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoForward extends Command {

    private double calculatedRotation;
	private PID rotation;
	private double y;
	private double x;
	double timer=1;
	double periods;

	public AutoForward(double x, double y, double periods ) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drivetrain);
		this.x=x;
		this.y=y;
		this.periods= periods*50;		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//set the goal angle to current angle
    	rotation.setTarget(Robot.drivetrain.getNavX().getAngle());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//figure out the nessasary correction fot the rotation
    	calculatedRotation = rotation.calculate(Robot.drivetrain.getNavX().getAngle());
    	//drive the robot using the stick for x, y, and the PID control for rotation
    	Robot.drivetrain.drive(x, y, calculatedRotation);
    	timer++;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (periods<=timer);
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
