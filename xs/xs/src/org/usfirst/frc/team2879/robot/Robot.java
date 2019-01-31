/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2879.robot;

import org.usfirst.frc.team2879.robot.commands.driveGo;
import org.usfirst.frc.team2879.robot.commands.liftMove;
import org.usfirst.frc.team2879.robot.subsystems.Cone;
import org.usfirst.frc.team2879.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2879.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final Lift lift = new Lift();
	public static final Cone cone = new Cone();
	
	public static OI oi;

	Command m_autonomousCommand;
	
	SendableChooser<Command> chooser = new SendableChooser<>();

	
	@Override
	public void robotInit() {
		oi = new OI();
		chooser.addDefault("DriveGo", new driveGo(0));
		chooser.addDefault("LiftMove", new liftMove(0));
		// chooser.addObject("My Auto", new MyAutoCommand());
		
		
		SmartDashboard.putData("Auto mode", chooser);
	}

	
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		m_autonomousCommand = chooser.getSelected();

		
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

		
		
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		
	}
}
