package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;
import org.usfirst.frc.team2879.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import PID.PID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GoingTheDistance extends Command {
	private WPI_TalonSRX[] talons = Robot.drivetrain.getTalons();
	private double[] rate = {0,0,0,0};
	private double[] encCorrection = {1,1,-1,-1};
	private PID angleKeeper;
	private PID speed;
	private double position;
	private double encoderAverage=0;
	private double maxRate;
	private int loopStable;
	private Timer clockboi;
	

/**
 * Reluctantly crouched at the starting line
Engines pumping and thumping in time
The green light flashes, the flags go up
Churning and burning, they yearn for the cup
They deftly maneuver and muscle for rank
Fuel burning fast on an empty tank
Reckless and wild, they pour through the turns
Their prowess is potent and secretly stern
As they speed through the finish, the flags go down
The fans get up and they get out of town
The arena is empty except for one man
Still driving and striving as fast as he can
The sun has gone down and the moon has come up
And long ago somebody left with the cup
But he's driving and striving and hugging the turns
And thinking of someone for whom he still burns
He's going the distance
He's going for speed
She's all alone (all alone)
All alone in her time of need
Because he's racing and pacing and plotting the course
He's fighting and biting and riding on his horse
He's going the distance
No trophy, no flowers, no flashbulbs, no wine
He's haunted by something he cannot define
Bowel-shaking earthquakes of doubt and remorse
Assail him, impale him with monster-truck force
In his mind, he's still driving, still making the grade
She's hoping in time that her memories will fade
'Cause he's racing and pacing and plotting the course
He's fighting and biting and riding on his horse
The sun has gone down and the moon has come up
And long ago somebody left with the cup
But he's striving and driving and hugging the turns
And thinking of someone for whom he still burns
'Cause he's going the distance
He's going for speed
She's all alone (all alone)
All alone in her time of need
Because he's racing and pacing and plotting the course
He's fighting and biting and riding on his horse
He's racing and pacing and plotting the course
He's fighting and biting and riding on his horse
He's going the distance
He's going for speed
He's going the distance
Ah no, so sad, alright 
Oh no, oh no, no, no
 * @param distance
 */
	public GoingTheDistance(double distance) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		this.maxRate=0.3;
		angleKeeper = new PID(.05,0,0);
		speed = new PID(0.00002,0.00002,.00010);
		angleKeeper.setTarget(Robot.drivetrain.getNavX().getAngle());
		speed.setTarget(distance);
		speed.setOkError(400);
		speed.setMaxInc(50);
		clockboi.start();
		clockboi.reset();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.setBrakeMode(true);
		for (WPI_TalonSRX t : talons) {
			/*t.configClosedloopRamp(1, 10);
			t.configNominalOutputForward(0, RobotMap.timeoutMs);
			t.configNominalOutputReverse(0, RobotMap.timeoutMs);
			t.configPeakOutputForward(1, RobotMap.timeoutMs);
			t.configPeakOutputReverse(-1, RobotMap.timeoutMs);
			
			  set closed loop gains in slot0
			 
			t.config_kF(0, 1, RobotMap.timeoutMs);
			t.config_kP(0, 0, RobotMap.timeoutMs);
			t.config_kI(0, 0, RobotMap.timeoutMs);
			t.config_kD(0, 0, RobotMap.timeoutMs);
			*/
		}
		position=0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		encoderAverage=0; 
		double velocity= speed.calculate(position);
		SmartDashboard.putNumber("velocity", velocity);
		if (velocity>maxRate) {
			velocity= maxRate;
		}
		if (velocity<-maxRate) {
			velocity= -maxRate;
		}
		rate[0]=velocity;
		rate[1]=velocity;
		rate[2]=velocity;
		rate[3]=velocity;
		
		for (int i = 0; i <= 3; i++) {
			talons[i].set(ControlMode.PercentOutput, rate[i]*encCorrection[i]);
			encoderAverage += talons[i].getSelectedSensorVelocity(0)*encCorrection[i]/4;
		}
		position += encoderAverage*(RobotMap.distancePerRevolution);
		SmartDashboard.putNumber("position", position);
		SmartDashboard.putNumber("encoderAverage", encoderAverage);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean done = false;;
		if(Math.abs(encoderAverage)<=2) {
			loopStable++;
			if (loopStable == 10) {
				done = true;
			};
		}else {
			loopStable=0;
		}
		return(done && (clockboi.get()>.5));

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
