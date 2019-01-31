/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2879.robot.subsystems;

import org.usfirst.frc.team2879.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	private WPI_TalonSRX[] talons;
	
	public WPI_TalonSRX[] getTalons(){
		return talons;
	}
	
	private RobotDrive drivetrain;
	double encoderedgesperrev = 6;
	
	public DriveTrain() {
		super("DriveTrain");
		initTalonsConfig();
		
	}
	
	private void initTalonsConfig() {
		// TODO Auto-generated method stub
		talons = new WPI_TalonSRX[] { new WPI_TalonSRX(RobotMap.frmotor), new WPI_TalonSRX(RobotMap.flmotor), new WPI_TalonSRX(RobotMap.brmotor), new WPI_TalonSRX(RobotMap.blmotor) };
		
		for (WPI_TalonSRX t : talons) {
			t.setNeutralMode(NeutralMode.Coast);
			t.setInverted(true);
			t.configSelectedFeedbackSensor(FeedbackDevice.Tachometer, 0, 100);
			t.enableVoltageCompensation(true);
			t.configSetParameter(430, encoderedgesperrev, 0, 0, 0);
			t.configSetParameter(431, 2, 0, 0, 0);
		}
		

		
		
	}
	
	@SuppressWarnings("deprecation")
	public void driveGo(double m, double angle){
		drivetrain.drive(m, angle);
	
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
