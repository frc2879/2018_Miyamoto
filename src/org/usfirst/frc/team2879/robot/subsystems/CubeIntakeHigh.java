package org.usfirst.frc.team2879.robot.subsystems;

import org.usfirst.frc.team2879.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntakeHigh extends Subsystem {
	Talon left = new Talon(RobotMap.topIntakeLeft);
	Talon right = new Talon(RobotMap.topIntakeright);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void succ() {
    	left.set(.5);
    	right.set(-.5);
    }
    
    public void ssspit() {
    	left.set(-.5);
    	right.set(.5);
    }
    
    public void stap() {
    	left.set(0);
    	right.set(0);
    }
}

