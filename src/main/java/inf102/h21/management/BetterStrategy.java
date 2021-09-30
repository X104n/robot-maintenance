package inf102.h21.management;

import java.util.LinkedList;
import java.util.List;

public class BetterStrategy extends AbstractStrategy {

    @Override
    protected void moveFreeRobots() {
        // TODO: This method could be suited for task 3
        double incrementX = (Math.sqrt(available.size()) * (furthestX - closestX)) / available.size(); //O(1)
        double incrementY = (Math.sqrt(available.size()) * (furthestY - closestY)) / available.size(); //O(1)
        int robotsUsed = 0;
        int numberInRow = 0;
        outerloop:
        for (int i = 0; ((i * incrementX) + closestX) < furthestX; i++) { //O(sqrt(n))
            if (i == 1) { //O(1)
                numberInRow = robotsUsed;
            }
            for (int j = 0; ((j * incrementY) + closestY) < furthestY; j++) { //O(sqrt(n))
                if (robotsUsed == available.size()) { //O(1)
                    break outerloop;
                }
                Robot robotToMove = available.get(j + i * numberInRow); //O(1)
                double locX = (incrementX * j) + closestX;
                double locY = (incrementY * i) + closestY;
                Location moveHere = new Location(locX, locY);
                robotToMove.move(moveHere); //O(log m)
                robotsUsed++;
            }
        }
    }

    public void reset() { //O(1)
        closestX = 1000;
        closestY = 1000;
        furthestX = 0.0;
        furthestY = 0.0;
    }

    private void updateLocations(Job job) { //O(1)
        if (job.location.x > furthestX) { //O(1)
            furthestX = job.location.x;
        }
        if (job.location.y > furthestY) { //O(1)
            furthestY = job.location.y;
        }
        if (job.location.x < closestX) { //O(1)
            closestX = job.location.x;
        }
        if (job.location.y < closestY) { //O(1)
            closestY = job.location.y;
        }
    }

    private double furthestX = 0.0;
    private double furthestY = 0.0;
    private double closestX = 1000;
    private double closestY = 1000;
    private double localTime = 0.0;

    @Override
    protected List<Robot> selectRobots(Job job) {
        if (job.time < localTime) { //O(1)
            reset(); //O(1)
        }
        localTime = job.time;


        //This mofo

        List<Robot> theChosenOnes = new LinkedList<>();
        if (available == null) {
            return null;
        }
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


        updateLocations(job); //O(1)


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
