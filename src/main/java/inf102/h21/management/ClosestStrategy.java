package inf102.h21.management;

import java.util.*;

public class ClosestStrategy extends AbstractStrategy {

    /**
     * This code is written in abstractStrategy because I use it in BetterStrategy as well.
     * @param job       - The job to select robots for
     * @return List of robots being sent to the job
     */
    @Override
    protected List<Robot> selectRobots(Job job) { //O(n log n) + O(k) = O(n log n)
        return super.selectRobots(job);
    }

    @Override
    public String getName() {
        return "Closest strategy";
    }
}
