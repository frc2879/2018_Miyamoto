package org.usfirst.frc.team2879.robot.subsystems;

import org.usfirst.frc.team2879.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Liftty extends Subsystem {
	
	WPI_TalonSRX redline;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Liftty(){
    	redline= new WPI_TalonSRX(RobotMap.lift);
    }
    
    public void set(double speed) {
    	redline.set(speed);
    }
    
    public double getDistance() {
    	redline.getSensorCollection().getPulseWidthRiseToRiseUs();
		return 0;
    	
    }
}

