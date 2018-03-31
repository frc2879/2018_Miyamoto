package org.usfirst.frc.team2879.robot;

import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2879.robot.commands.GoingTheDistance;
import org.usfirst.frc.team2879.robot.commands.RevisedAuto;
import org.usfirst.frc.team2879.robot.subsystems.CubeIntakeHigh;
import org.usfirst.frc.team2879.robot.subsystems.CubeIntakeLow;
import org.usfirst.frc.team2879.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2879.robot.subsystems.Liftty;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveTrain drivetrain
		= new DriveTrain();
	public static OI oi;
	public static final CubeIntakeLow cubeIntakeLow
		= new CubeIntakeLow();
	public static final CubeIntakeHigh cubeIntakeHigh
		= new CubeIntakeHigh();
	public static final Liftty lift
	= new Liftty();

	Command autonomousCommand;

	SendableChooser<String> StartPosition;
	SendableChooser<String> LeftScore;
	SendableChooser<String> RightScore;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		StartPosition = new SendableChooser<>();
		RightScore = new SendableChooser<>();
		LeftScore = new SendableChooser<>();
		
		CameraServer camera = CameraServer.getInstance();
		VideoSource lowcam = camera.startAutomaticCapture("cam0", 0);
		VideoSource highcam = camera.startAutomaticCapture("cam1", 1);
		highcam.setResolution(16,9);
		highcam.setFPS(25);
		lowcam.setResolution(16,9);
		lowcam.setFPS(25);
		oi = new OI();
		StartPosition.addObject("center", "c");
		StartPosition.addObject("left", "l");
		StartPosition.addObject("right", "r");
		StartPosition.addDefault("center", "c");
		LeftScore.addObject("leftLow", "leftLow");
		LeftScore.addObject("leftHigh", "leftHigh");
		LeftScore.addObject("leftMidLow","leftMidLow" );
		LeftScore.addObject("leftMidHigh","leftMidHigh" );
		LeftScore.addObject("mobility","mobility" );
		LeftScore.addDefault("mobility", "mobility");
		RightScore.addObject("rightMidLow", "rightMidLow");
		RightScore.addObject("rightHigh", "rightHigh");
		RightScore.addObject("rightMidHigh", "rightMidHigh");
		RightScore.addObject("rightLow", "rightLow");
		RightScore.addObject("mobility", "mobility");
		RightScore.addDefault("mobility", "mobility");
		SmartDashboard.putData("Start position", StartPosition);
		SmartDashboard.putData("left score", LeftScore);
		SmartDashboard.putData("right score", RightScore);
		//RightScore.addObject();
		// chooser.addObject("My Auto", new MyAutoCommand());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		

		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		if (LeftScore.getSelected() == "mobility" || RightScore.getSelected() == "mobility"){
			autonomousCommand = new GoingTheDistance(180);
		}else {
			autonomousCommand = new RevisedAuto(StartPosition.getSelected(), LeftScore.getSelected(), RightScore.getSelected());
		}
			/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new DriveMecanumStick(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("encodervelocityFL", drivetrain.getTalons()[0].getSelectedSensorVelocity(0)/1.7);
		SmartDashboard.putNumber("encodervelocityRL", drivetrain.getTalons()[1].getSelectedSensorVelocity(0)/1.7);
		SmartDashboard.putNumber("encodervelocityFR", drivetrain.getTalons()[2].getSelectedSensorVelocity(0)/1.7);
		SmartDashboard.putNumber("encodervelocityRR", drivetrain.getTalons()[3].getSelectedSensorVelocity(0)/1.7);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
