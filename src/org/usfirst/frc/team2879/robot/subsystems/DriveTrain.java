
package org.usfirst.frc.team2879.robot.subsystems;

import org.usfirst.frc.team2879.robot.Robot;
import org.usfirst.frc.team2879.robot.RobotMap;
import org.usfirst.frc.team2879.robot.commands.DriveMecanumStick;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;
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
	private AHRS navX;

	
	public DriveTrain() {
        super("DriveTrain");
        initTalonsConfig();
        initNavX();
    }
	
	public void initNavX() {
		//make sure navx is attached to the rio via spi
		try {setNavX(new AHRS(SPI.Port.kMXP)); 
	      } catch (RuntimeException ex ) {
	          DriverStation.reportError("ya dun goofed: no navx found :  " + ex.getMessage(), true);
	      }
	}
	
 	public void initTalonsConfig() {
		talons= new WPI_TalonSRX[] {
                new WPI_TalonSRX(RobotMap.frontleftmotor),
                new WPI_TalonSRX(RobotMap.rearleftmotor),
                new WPI_TalonSRX(RobotMap.frontrightmotor),
                new WPI_TalonSRX(RobotMap.rearrightmotor)
		};
                
        for (WPI_TalonSRX t: talons) {
        	t.setNeutralMode(NeutralMode.Coast);
        	t.setInverted(true);
        }
      
		
        drivetrain = new MecanumDrive(talons[0], talons[1], talons[2], talons[3]);
        drivetrain.setSafetyEnabled(false);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveMecanumStick(1));
	}
	
 	public void drive(double x, double y, double rotation) {
        drivetrain.driveCartesian( x, -y, rotation,0.0);
	}
	
 	public void drivePolar(double magnitude, double angle, double rotation) {
        drivetrain.drivePolar( magnitude, angle, rotation);
	}
 	
	public void drive(Joystick joy,double speed) {
        drive(speed*Robot.oi.getStickX(), speed*Robot.oi.getStickY(), speed*Robot.oi.getStickTwist());
	}

	public AHRS getNavX() {
		return navX;
	}
	
	public void setNavX(AHRS navX) {
		this.navX = navX;
	}

	public void changeBrakeMode(boolean brake) {
		
		if (brake) {		
		for (WPI_TalonSRX t: talons) {
			t.setNeutralMode(NeutralMode.Brake);
		}}else {
			for (WPI_TalonSRX t: talons) {
				t.setNeutralMode(NeutralMode.Coast);
		}}
	}
}