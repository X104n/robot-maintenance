package inf102.h21.management;

import java.util.*;

public class RandomStrategy extends AbstractStrategy {

    /**
     * Shuffles the available list the chooses the k first robots
     * that is needed for the job from that list
     *
     * @param job       - The job to select robots for
     * @return List of all the robots that is being sent to the job
     */
    @Override
    protected List<Robot> selectRobots(Job job) { //O(n + k)
        List<Robot> theChosenOnes = new LinkedList<Robot>(); //O(1)
        Collections.shuffle(available); //O(n)
        if (available.size() >= job.robotsNeeded && available.size() > 0) { //O(1) + O(1)
            for (int i = 0; i < job.robotsNeeded; i++) { //O(k)
                theChosenOnes.add(available.get(i)); //O(1) + O(1)
            }
        }
        return theChosenOnes;
    }

    @Override
    public String getName() {
        return "Random strategy";
    }
}
