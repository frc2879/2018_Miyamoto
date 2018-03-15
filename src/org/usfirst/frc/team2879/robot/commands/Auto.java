package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Auto extends CommandGroup {
    public Auto(String location, String leftScore, String rightScore) {
    	String side="l";
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		//verify message
		if(gameData.length() > 0){
			if(gameData.charAt(0) == 'L'){
				side="l";
			}else{
				side="r";
			}
		}
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	requires(Robot.drivetrain);
    	requires(Robot.cubeIntakeHigh);
    	addParallel(new ConstantIntake(0.15, .25, true));
		addSequential(new GoingTheDistance(50000));
    	if(location=="c") {
    		
    		if (side=="l") {
    			if(leftScore=="h") {
    				//clh
	    			addSequential(new RotateToAngle(-90));
	    			addSequential(new GoingTheDistance(50000));
	    			addSequential(new RotateToAngle(-90));
	    			addSequential(new GoingTheDistance(-50000));
    			}
    			if(leftScore=="l") {
    				//cll
    				addSequential(new GoingTheDistance(50000));
    				addSequential(new RotateToAngle(90));
    				addSequential(new GoingTheDistance(-80000));
    			
    			}

    		
    		}else if (side=="r") {
    			if(rightScore=="h") {
    	    		//crh
	    			addSequential(new RotateToAngle(90));
	    			addSequential(new GoingTheDistance(50000));
		    		addSequential(new RotateToAngle(-90));
		    		addSequential(new GoingTheDistance(50000));
    			}
    			if(rightScore=="l") {
    				//crl
    				addSequential(new GoingTheDistance(50000));
    				addSequential(new RotateToAngle(90));
    				addSequential(new GoingTheDistance(80000)); 
    				
    				
    			
    			}
	    		
	    	}
	    	
	    	
	    }
    	if(location=="r") {
    		if (side=="r") {
        		if(rightScore=="h") {
            		//rrh
        			addSequential(new GoingTheDistance(100000));
        		}
        		if(rightScore=="l") {
            		//rrl
        			addSequential(new GoingTheDistance(50000));
        			addSequential(new RotateToAngle(90));

    			}
        		
        	} else if (side=="l") {
        		if(leftScore=="h") {
            		//rlh
	        		addSequential(new GoingTheDistance(50000));
	        		addSequential(new RotateToAngle(-90));
	        		addSequential(new GoingTheDistance(100000));
	        		addSequential(new RotateToAngle(-90));
	        		addSequential(new GoingTheDistance(-50000));
        		}
        		if(leftScore=="l") {
            		//rll
        			addSequential(new GoingTheDistance(50000));
    				addSequential(new RotateToAngle(90));
    				addSequential(new GoingTheDistance(-80000));	
    				
        			
    			}
        		
        	}
    		
    	}
    	if(location=="l") {
    		if (side=="l") {	
    			if(leftScore=="h") {
    	    		//llh
    				addSequential(new GoingTheDistance(100000));
    				addSequential(new RotateToAngle(180));
    			}
    			if(leftScore=="l") {
    				//lll
    				addSequential(new GoingTheDistance(50000));
        			addSequential(new RotateToAngle(90));
    			}
        	} else if (side=="r") {
        		if(rightScore=="h") {
            		//lrh
	        		addSequential(new GoingTheDistance(50000));
	        		addSequential(new RotateToAngle(90));
	        		addSequential(new GoingTheDistance(100000));
	        		addSequential(new RotateToAngle(-90));
	        		addSequential(new GoingTheDistance(50000));
        		}
        		if(rightScore=="l") {
    				//lrl
    				addSequential(new GoingTheDistance(50000));
    				addSequential(new RotateToAngle(90));
    				addSequential(new GoingTheDistance(100000));
    				
    		
        			
    			}
        	}
    		
    	}
    	addSequential(new HoverSlam(-.4));
    	addSequential(new ConstantIntake(-.5,true));
    	
    	
    }
}
