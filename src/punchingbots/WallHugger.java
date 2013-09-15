package punchingbots;

import robocode.Robot;

public class WallHugger extends Robot {

	double leftX;
	double rightX;
	double bottomY;
	double topY;

	public void turnTo(double toHeading) {
		while (toHeading > 360) {
			toHeading -= 360;
		}

		double heading = getHeading();

		// TODO: figure out which direction is closer
		turnLeft(heading - toHeading);
	}

	@Override
	public void run() {
		leftX = 0 + getWidth() / 2;
		rightX = getBattleFieldWidth() - getWidth() / 2;
		bottomY = 0 + getHeight() / 2;
		topY = getBattleFieldHeight() - getWidth() / 2;

		double x = getX();
		double y = getY();

		double dLeft = Math.abs(leftX - x);
		double dRight = Math.abs(rightX - x);
		double dBottom = Math.abs(bottomY - y);
		double dTop = Math.abs(topY - y);

		if (dLeft <= dRight && dLeft <= dBottom && dLeft <= dTop) {
			turnTo(270);
			ahead(dLeft);
			turnLeft(90);
			ahead(dBottom);
		} else if (dRight <= dBottom && dRight <= dTop) {
			turnTo(90);
			ahead(dRight);
			turnLeft(90);
			ahead(dTop);
		} else if (dBottom <= dTop) {
			turnTo(180);
			ahead(dBottom);
			turnLeft(90);
			ahead(dRight);
		} else {
			turnTo(0);
			ahead(dTop);
			turnLeft(90);
			ahead(dLeft);
		}

		while (true) {
			turnLeft(90);
			if(getHeading() == 0 || getHeading() == 180){
				ahead(getBattleFieldHeight());
			}else{
				ahead(getBattleFieldWidth());
			}
		}
	}

}
