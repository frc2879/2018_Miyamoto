
package org.usfirst.frc.team2879.robot.subsystems;

import org.usfirst.frc.team2879.robot.Robot;
import org.usfirst.frc.team2879.robot.RobotMap;
import org.usfirst.frc.team2879.robot.commands.DriveMecanumStick;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
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
	private Encoder[] encoders;
	
	public DriveTrain() {
        super("DriveTrain");
        initTalonsConfig();
        initNavX();
    }
	
	private void initNavX() {
		//make sure navx is attached to the rio via spi
		try {setNavX(new AHRS(SPI.Port.kMXP)); 
	      } catch (RuntimeException ex ) {
	          DriverStation.reportError("ya dun goofed: no navx found :  " + ex.getMessage(), true);
	      }
	}
	
 	private void initTalonsConfig() {
		talons= new WPI_TalonSRX[] {
                new WPI_TalonSRX(RobotMap.frontleftmotor),
                new WPI_TalonSRX(RobotMap.rearleftmotor),
                new WPI_TalonSRX(RobotMap.frontrightmotor),
                new WPI_TalonSRX(RobotMap.rearrightmotor)
		};
		
		encoders = new Encoder[] {
			new Encoder(0,1,false,CounterBase.EncodingType.k4X),
			new Encoder(0,1,false,CounterBase.EncodingType.k4X),
			new Encoder(0,1,false,CounterBase.EncodingType.k4X),
			new Encoder(0,1,false,CounterBase.EncodingType.k4X)
		};
                
        for (WPI_TalonSRX t: talons) {
        	t.setNeutralMode(NeutralMode.Coast);
        	t.setInverted(true);  	
        }
        
        for (Encoder t:encoders) {
        	t.setMaxPeriod(RobotMap.maxRate);
        	t.setMinRate(RobotMap.minRate);
        	t.setDistancePerPulse(RobotMap.distancePerPulse);
        	t.setSamplesToAverage(10);
        }
		
        drivetrain = new MecanumDrive(talons[0], talons[1], talons[2], talons[3]);
        //we need this
        drivetrain.setSafetyEnabled(false);
	}
 	
 	/**
 	 * this goes from the desired motion to the motor speeds.  
 	 * @param x the desired x translation
 	 * @param y th desired y translation
 	 * @param z the desired rotation
 	 * @return an array with the motor velocities in a range from 1 to -1 in the order fl, rl, fr, rr
 	 */
 	public double[] inverseKinematicsCalculate(double x, double y, double z) {
 		double[] outputs = {x+y+z,-x+y+z,x-y+z,-x-y+z};
 		double maxMagnitude = Math.abs(outputs[0]);
 		
 		//determine the max output magnitude.
 		for (int i = 1; i < 4; i++) {
 			double temp = Math.abs(outputs[i]);
 			if (maxMagnitude < temp) {
 		        maxMagnitude = temp;
 		    }
 		}
 		
 		//if the largest magnitude is too big, scale everything
 		if (maxMagnitude > 1.0) {
 			for (int i = 0; i < outputs.length; i++) {
 				outputs[i] = outputs[i] / maxMagnitude;
 			}
 		} 		
 		
 		return outputs;
 	}
 	
 	/**
 	 * this method takes motor rotation and turns it into robot motion
 	 * @param fl front left motor rotation
 	 * @param rl
 	 * @param fr
 	 * @param rr
 	 * @return returns an array of the motion (x,y, rotation)
 	 */
 	public double[] forwardKinematicCalculate(double fl, double rl,double fr,double rr) {
		double[] outputs = {fl-rl+fr-rr, fl+rl-fr-rr, fl+rl+fr+rr}; 
		//scale by one quarter to make it be an inverse of inverse kinematics;
		outputs[0] /= 4;
		outputs[1] /= 4;	
		outputs[2] /= 4;
		return outputs; 	
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
	
	/**
	 * this will change the motors between brake mode and coast mode 
	 * @param brake set true for brake mode, false for coast
	 */
	public void setBrakeMode(boolean brake) {
		
		if (brake) {		
		for (WPI_TalonSRX t: talons) {
			t.setNeutralMode(NeutralMode.Brake);
		}}else {
			for (WPI_TalonSRX t: talons) {
				t.setNeutralMode(NeutralMode.Coast);
		}}
	}
}