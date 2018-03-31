package org.usfirst.frc.team2879.robot.commands;

import org.usfirst.frc.team2879.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * class to handle the auto
 */
public class RevisedAuto extends CommandGroup {

	public RevisedAuto(String start, String leftScorePosition, String rightScorePosition) {
		requires(Robot.drivetrain);
		requires(Robot.cubeIntakeHigh);
		requires(Robot.cubeIntakeLow);
		addParallel(new ConstantIntake(0.15, .25, true));
		String side = "l";
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		// verify message
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				side = "l";
			} else {
				side = "r";
			}
		}
		double[] movements = { 0, 0, 0, 0, 0, 0 };
		if (side == "l") {
			movements = doLogic(start, leftScorePosition);
		}
		if (side == "r") {
			movements = doLogic(start, rightScorePosition);
		}
		/**
		SmartDashboard.putString("startposition", start);
		SmartDashboard.putNumber("movements[0]", movements[0]);
		SmartDashboard.putNumber("movements[1]", movements[1]);
		SmartDashboard.putNumber("movements[2]", movements[2]);
		SmartDashboard.putNumber("movements[3]", movements[3]);
		SmartDashboard.putNumber("movements[4]", movements[4]);
		SmartDashboard.putNumber("movements[5]", movements[5]);
		*/
		// {primary movement, turn angle, lateral movement, turn angle 2, secondary
		// movement, orient}
		if (movements[0] != 0) {
			addSequential(new GoingTheDistance(movements[0]));
		}
		if (movements[1] != 0) {
			addSequential(new RotateByAngle(movements[1]));
		}
		if (movements[2] != 0) {
			addSequential(new GoingTheDistance(movements[2]));
		}
		if (movements[3] != 0) {
			addSequential(new RotateByAngle(movements[3]));
		}
		if (movements[4] != 0) {
			addSequential(new GoingTheDistance(movements[4]));
		}
		if (movements[5] != 0) {
			addSequential(new RotateByAngle(movements[5]));
		}
		// final outspit
		addSequential(new HoverSlam(-.75));
		addSequential(new ConstantIntake(-.75, true));
	}

	private double[] doLogic(String start, String scorePosition) {
		// {primary movement, turn angle, lateral movement, turn angle 2, secondary
		// movement, orient}
		double[] movements = { 0, 0, 0, 0, 0, 0 };

		if (start == "l") {
			switch (scorePosition) {
			case "leftLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = 60.55;
				movements[3] = -90;
				movements[4] = 49;
				movements[5] = 90;
				break;
			case "rightLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = 167.95;
				movements[3] = -90;
				movements[4] = 49;
				movements[5] = 90;
				break;
			case "leftMidLow":
				movements[0] = 148.75;
				movements[1] = 180;
				break;
			case "rightMidLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = 228.5;
				movements[3] = -90;
				movements[4] = 104.0;
				break;
			case "leftHigh":
				movements[0] = 218.75;
				movements[1] = -90;
				movements[2] = -60.55;
				break;
			case "rightHigh":
				movements[0] = 218.75;
				movements[1] = -90;
				movements[2] = -167.95;
				break;
			case "leftMidHigh":
				movements[0] = 148.75;
				movements[1] = 180;
				break;
			case "rightMidHigh":
				movements[0] = 218.75;
				movements[1] = -90;
				movements[2] = 228.5;
				movements[3] = 90;
				movements[4] = -70;
				break;
			case "mobility" :
				movements[0] = 200;
				break;
			}
		}
		if (start == "c") {
			switch (scorePosition) {

			case "leftLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = -55;
				movements[3] = -90;
				movements[4] = 49;
				movements[5] = 90;
				break;
			case "rightLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = 47.95;
				movements[3] = -90;
				movements[4] = 49;
				movements[5] = 90;
				break;
			case "leftMidLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = -120;
				movements[3] = 90;
				movements[4] = -104;
				break;
			case "rightMidLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = 108.5;
				movements[3] = -90;
				movements[4] = 104;
				break;
			case "leftHigh": // these are only here for structure
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = -120;
				movements[3] = 90;
				movements[4] = -104;
				break;
			case "rightHigh":// these are only here for structure
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = 108.5;
				movements[3] = -90;
				movements[4] = 104;
				break;
			case "leftMidHigh":// these are only here for structure
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = -120;
				movements[3] = 90;
				movements[4] = -104;
				break;
			case "rightMidHigh":// these are only here for structure
				movements[0] = 44.75;
				movements[1] = 90;
				movements[2] = 108.5;
				movements[3] = -90;
				movements[4] = 104;
				break;
			}
		}
		if (start == "r") {
			switch (scorePosition) {
			case "leftLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = -167.95;
				movements[3] = -90;
				movements[4] = 49;
				movements[5] = 90;
				break;
			case "rightLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = -60.55;
				movements[3] = -90;
				movements[4] = 49;
				movements[5] = 90;
				break;
			case "leftMidLow":
				movements[0] = 40;
				movements[1] = 90;
				movements[2] = -228.5;
				movements[3] = 90;
				movements[4] = -104.0;
				break;
			case "rightMidLow":
				movements[0] = 148.75;
				break;
			case "leftHigh":
				movements[0] = 218.75;
				movements[1] = -90;
				movements[2] = 167.95;
				break;
			case "rightHigh":
				movements[0] = 218.75;
				movements[1] = -90;
				movements[2] = 60.55;
				break;
			case "leftMidHigh":
				movements[0] = 218.75;
				movements[1] = -90;
				movements[2] = 228.5;
				movements[3] = -90;
				movements[4] = 70;
				break;
			case "rightMidHigh":
				movements[0] = 148.75;
				break;
			case "mobility" :
				movements[0] = 200;
				break;
			}
		//movements[0] *= 1.1;
		//movements[2] *= 1.1;
		//movements[4] *= 1.1;
		}

		// final approach

		return movements;
	}
}
