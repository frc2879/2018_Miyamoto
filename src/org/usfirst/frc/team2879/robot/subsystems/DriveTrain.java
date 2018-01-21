
package org.usfirst.frc.team2879.robot.subsystems;

import org.usfirst.frc.team2879.robot.RobotMap;
import org.usfirst.frc.team2879.robot.commands.DriveMecanumStick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * drivetrain for the !!!SQUARE!!! :) mecanum drive base.
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	 private WPI_TalonSRX[] talons;
	 private MecanumDrive drivetrain;
	
	public DriveTrain() {
        super("DriveTrain");
        initTalonsConfig();

    }
	
	public void initTalonsConfig() {
		talons= new WPI_TalonSRX[] {
                new WPI_TalonSRX(RobotMap.frontleftmotor),
                new WPI_TalonSRX(RobotMap.rearleftmotor),
                new WPI_TalonSRX(RobotMap.frontrightmotor),
                new WPI_TalonSRX(RobotMap.rearrightmotor)
        };
		
        drivetrain = new MecanumDrive(talons[0], talons[1], talons[2], talons[3]);
	}
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveMecanumStick());
	}
	
	public void drive(double x, double y, double rotation) {
        drivetrain.driveCartesian( x, y, rotation,0.0);
	}
	public void drive(Joystick joy) {
        drive(joy.getX(), joy.getY(),joy.getTwist());
	}
}