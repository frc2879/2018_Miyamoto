package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import PID.PID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RateFollow extends Command {
	private WPI_TalonSRX[] talons = Robot.drivetrain.getTalons();
	private double[] rate;
	private double[] rotations = { 0, 0, 0, 0 };;
	private PID angleKeeper;
	private double rotation;

	public RateFollow(double distance) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		rotation = angleKeeper.calculate(Robot.drivetrain.getNavX().getAngle());
		rotations[0] = rotation;
		rotations[1] = rotation;
		rotations[2] = -rotation;
		rotations[3] = -rotation;

		for (int i = 0; i <= 3; i++) {
			talons[i].set(ControlMode.Velocity, rate[i] + rotations[i]);
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
