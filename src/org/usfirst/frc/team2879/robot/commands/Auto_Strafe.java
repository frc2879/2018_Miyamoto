package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import PID.PID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Auto_Strafe extends Command {
	//creates the pid controllers, see PID class to understand what the arguments are
	public PID rotation = new PID (0,0,0);
	public PID xPID = new PID (1,0,0);
	public PID yPID = new PID (0,0,0);
	double deltax,deltay,outX,outY,outRotate,xCurr,yCurr,angleCurr;
	
	/**
	 * this drives the robot to a specific location on the field, then stops
	 * because of how the navX calculates displacement error compounds quickly
	 * only use this for short movements (under 3 seconds)
	 * @param deltax the change in x (horizontal, in meters) relative to the current orientation of the robot 
	 * @param deltay the change in y (vertical, in meters) relative to the current orientation of the robot 
	 */
    public Auto_Strafe(double deltax, double deltay) {
    	requires(Robot.drivetrain);
    	this.deltax = deltax;
    	this.deltay = deltay; 	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//reset the navx displacement so that it is accurate. 
    	Robot.drivetrain.getNavX().zeroYaw();
    	Robot.drivetrain.getNavX().resetDisplacement();
    	
    	//set targets for the pid
    	rotation.setTarget(Robot.drivetrain.getNavX().getAngle());
    	xPID.setTarget(deltax+Robot.drivetrain.getNavX().getDisplacementX());
    	yPID.setTarget(deltay+Robot.drivetrain.getNavX().getDisplacementY());
    	
    	Robot.drivetrain.setBrakeMode(true);
    	
    	//the pid will stop if it is within 1 tenth of a meter for 60ms
    	xPID.setOkError(.2);
    	yPID.setOkError(.2);
    	rotation.setOkError(5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//get the current readings of x, y, and angle.
    	xCurr = Robot.drivetrain.getNavX().getDisplacementX();
    	yCurr = Robot.drivetrain.getNavX().getDisplacementY();
    	angleCurr = Robot.drivetrain.getNavX().getAngle();
    	
    	//calculate the pid values
    	outX = xPID.calculate(xCurr);
    	outY = yPID.calculate(yCurr);
    	outRotate = rotation.calculate(angleCurr);
    	
    	//use the pid values
    	Robot.drivetrain.drive(outX, outY, outRotate);
    	
    	//put stuff on the smart dashboard for testing 
    	SmartDashboard.putNumber("xCurr", xCurr);
    	SmartDashboard.putNumber("yCurr", yCurr);
    	SmartDashboard.putNumber("angleCurr", angleCurr);
    	SmartDashboard.putNumber("outX", outX);
    	SmartDashboard.putNumber("outY", outY);
    	SmartDashboard.putNumber("outRotate", outRotate);
    	SmartDashboard.putNumber("x error", xPID.getError());
    	SmartDashboard.putNumber("y error", yPID.getError());
    	SmartDashboard.putNumber("angle error", rotation.getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//checks if all three PIDs are at the target.
        return (xPID.atTarget() && yPID.atTarget() && rotation.atTarget());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0,0,0);
    	Robot.drivetrain.setBrakeMode(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
