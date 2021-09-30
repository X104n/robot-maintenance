package inf102.h21.management;

import java.util.*;

public class ClosestStrategy extends AbstractStrategy {

    @Override
    protected List<Robot> selectRobots(Job job) { //O(n log n) + O(k) = O(n log n)
        List<Robot> theChosenOnes = new LinkedList<>(); // O(1)
        PriorityQueue<Robot> closestOfAvailable = new PriorityQueue<>(new Comparator<Robot>() {
            @Override
            public int compare(Robot Robot1, Robot Robot2) {
                double distance1 = Robot1.getLocation().dist(job.location);
                double distance2 = Robot2.getLocation().dist(job.location);
                if (distance1 > distance2)
                    return 1;
                else if (distance1 < distance2)
                    return -1;
                else
                    return 0;
            }
        });
        if (available.size() >= job.robotsNeeded && available.size() > 0) { //O(1)
            closestOfAvailable.addAll(available); // O(n log n)
            for (int i = 0; i < job.robotsNeeded; i++) { //O(k)
                theChosenOnes.add(closestOfAvailable.poll()); // O(1)
            }
        }
        return theChosenOnes;
    }

    @Override
    public String getName() {
        return "Closest strategy";
    }

}
