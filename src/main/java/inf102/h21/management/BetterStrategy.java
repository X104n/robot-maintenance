package inf102.h21.management;

import java.util.LinkedList;
import java.util.List;

public class BetterStrategy extends AbstractStrategy {

	@Override
	protected List<Robot> selectRobots(Job job) {
		List<Robot> theChosenOnes = new LinkedList<>();
		if (available.size() <= 0) {
			return null;
		}
		if (available.size() < job.robotsNeeded) {
			return null;
		}
		//Find the robot that is the furthest away
		Robot far = available.get(0);
		for (Robot robot : available) {
			if (distance(far, job) < distance(robot, job)) {
				far = robot;
			}
		}
		for (int i = 0; i < job.robotsNeeded; i++) {
			Robot closest = far;
			for (Robot robot : available) {
				if (!theChosenOnes.contains(robot)) {
					if (distance(closest, job) >= distance(robot, job)) {
						closest = robot;
					}
				}
			}
			theChosenOnes.add(closest);
		}
//        System.out.println(job.robotsNeeded);
//        System.out.println(available.size());
//        System.out.println(theChosenOnes);
//        System.out.println(job.location);
		return theChosenOnes;
	}

	private double distance(Robot robot, Job job) {
		return robot.getLocation().dist(job.location);
	}

	@Override
	public String getName() {
		return "Better strategy";
	}

}
