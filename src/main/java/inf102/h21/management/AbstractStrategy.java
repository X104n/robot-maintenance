package inf102.h21.management;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractStrategy implements IStrategy {

    /**
     * List of all robots, both available and occupied
     */
    protected List<Robot> robots;
    protected List<Robot> available;
    /**
     * List of jobs not yet executed
     */
    protected Queue<Job> backLog;

    public AbstractStrategy() { //O(1)
        backLog = new LinkedList<Job>();
    }

    @Override
    public void registerRobots(List<Robot> robots) { //O(n)
        this.robots = new ArrayList<Robot>(robots); //O(n)
        available = new ArrayList<>(robots); //O(n)
    }

    @Override
    public void registerNewJob(Job job) { //O(n + ?)
        backLog.add(job); //O(1)
        doJobs(); //O(?)
    }

    @Override
    public void registerJobAsFulfilled(Job job, List<Robot> robots) { //O(n + ?)
        available.addAll(robots); //O(n)
        doJobs(); //O(?)
    }

    /**
     * Finds jobs in backLog and assigns robots
     */
    protected void doJobs() { //O(m(? + kn + m)) = O(m? + mkn + m^2)

        while (!backLog.isEmpty()) { //O(m)
            Job job = selectJob(); //O(1)
            List<Robot> selected = selectRobots(job); //O(?)

            if (assignRobots(selected, job)) //O(kn)
                removeJob(job); //O(m)
            else
                break;
        }
        if (backLog.isEmpty())  //O(1)
            moveFreeRobots(); //O(?)
    }

    /**
     * Selects a Job from the list of available jobs
     * Currently selects the job at the top of the list
     *
     * @return most appropriate job
     */
    protected Job selectJob() { //O(1)
        return backLog.peek(); //O(1)
    }

    protected void removeJob(Job job) { //O(m)
        if (backLog.peek().equals(job)) //O(1) + O(1)
            backLog.poll(); //O(1)
        else
            backLog.remove(job); //O(m)
    }

    /**
     * Select robots for the job. Should select robots most appropriate for the job.
     *
     * @param job       - The job to select robots for
     * @param available - The Robots to select among
     * @return return list of selected robots if the job can be executed, else return empty list
     */
    protected abstract List<Robot> selectRobots(Job job);

    /**
     * When a Robot is not assigned to a Job it is just waiting
     * We can then position these robots such that when a new job comes in
     * the robots are already close to the job.
     */
    protected void moveFreeRobots() {
    }

    /**
     * Sends the selected Robots to their Job
     *
     * @return true if robots assigned to job, false if not
     */
    boolean assignRobots(List<Robot> selected, Job job) { //O(n) + O(k(log m + n)) = O(k*log m + kn) = O(kn)
        if (selected == null) //O(1)
            return false;
        if (selected.isEmpty()) //O(1)
            return false;

        boolean canDo = selected.size() >= job.robotsNeeded; //O(1)
        for (Robot r : selected) { //O(n)
            if (r.isBusy()) { //O(1)
                System.out.println("You selected a robot that was busy.");
                canDo = false;
            }
        }
        if (canDo) { //O(1)
            for (Robot robot : selected) { //O(k)
                robot.move(job); //O(log m)
                available.remove(robot); //O(n)
            }
        } else {
            for (Robot r : selected) { //O(k)
                if (!r.isBusy()) { //O(1)
                    r.move(job.location); //O(log m)
                }
            }
        }
        return canDo;
    }

    /**
     * Returns list of free robots
     *
     * @return list of all available robots
     */
    public List<Robot> getAvailableRobots() { //O(n)
        return new ArrayList<Robot>(available);
    }

}
