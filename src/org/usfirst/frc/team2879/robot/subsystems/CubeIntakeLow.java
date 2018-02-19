package org.usfirst.frc.team2879.robot.subsystems;

import org.usfirst.frc.team2879.robot.RobotMap;
import org.usfirst.frc.team2879.robot.commands.ConstantIntake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntakeLow extends Subsystem {
	Talon left = new Talon(RobotMap.bottomIntakeleft);
	Talon right = new Talon(RobotMap.bottomIntakeright);

	private DigitalInput limitSwitch = new DigitalInput(1);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ConstantIntake(0.15, .25, false));
	}

	public void go(double speed) {
		left.set(-speed);
		right.set(speed);
	}

	public DigitalInput getLimitswitch() {
		return limitSwitch;
	}
}
