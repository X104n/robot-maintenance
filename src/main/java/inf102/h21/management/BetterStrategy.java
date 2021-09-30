package inf102.h21.management;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class BetterStrategy extends AbstractStrategy {

    private double furthestX = 0.0;
    private double furthestY = 0.0;
    private double closestX = 1000;
    private double closestY = 1000;
    private double localTime = 0.0;

    /**
     * This method spreads all the robots with an even distance between themselves
     * based on how big the interval of the furthest(x, y) and closest(x, y) is.
     *
     */
    @Override
    protected void moveFreeRobots() { //O(sqrt(n)(sqrt(n)(log m))) = O(n log m)
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
                double locX = (incrementX * j) + closestX; //O(1)
                double locY = (incrementY * i) + closestY; //O(1)
                Location moveHere = new Location(locX, locY);
                robotToMove.move(moveHere); //O(log m)
                robotsUsed++;
            }
        }
    }

    /**
     * Here i first check if a new simulation is run. Would like to do it in a different way,
     * but didn't find a solution in time for the deadline 🗿.
     * The closestStrategy is utilized in this code.
     * @param job       - The job to select robots for
     * @return list of robots being sent to the job
     */
    @Override
    protected List<Robot> selectRobots(Job job) {
        if (job.time < localTime) { //O(1)
            reset(); //O(1)
        }
        localTime = job.time;
        List<Robot> returnList = super.selectRobots(job);
        updateLocations(job);
        return returnList;
    }

    /**
     * Resets all the variables that keeps control on the outermost points of the jobs
     */
    public void reset() { //O(1)
        closestX = 1000;
        closestY = 1000;
        furthestX = 0.0;
        furthestY = 0.0;
    }

    /**
     * This method checks if the job that is specified is one of the outer most points
     *
     * @param job The job that is checked
     */
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

    @Override
    public String getName() {
        return "Better strategy";
    }

}
