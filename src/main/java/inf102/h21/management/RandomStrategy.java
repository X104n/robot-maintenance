package inf102.h21.management;

import java.util.*;

public class RandomStrategy extends AbstractStrategy {

    @Override
    protected List<Robot> selectRobots(Job job) {
        List<Robot> theChosenOnes = new ArrayList<>();
        Collections.shuffle(available);
        if (available.size() >= job.robotsNeeded && available.size() > 0) {
            for (int i = 0; i < job.robotsNeeded; i++) {
                theChosenOnes.add(available.get(i));
            }
        }
        return theChosenOnes;
    }

    @Override
    public String getName() {
        return "Random strategy";
    }
}
