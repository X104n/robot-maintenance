package inf102.h21.management;

import java.util.List;

/**
 * This interface describes the 3 methods you need to implement in order to design a strategy for the robots
 * 
 * 
 * @author Olav Bakken og Martin Vatshelle
 *
 */
public interface IStrategy {
	
	/**
	 * @return The name given to this strategy.
	 */
	public String getName();
	
	/**
	 * This method informs the strategy of which robots are available.
	 * Each robot is placed at a starting position and can be moved with the move commands.
	 * 
	 * Store the list of robots for executing jobs later.
	 * 
	 * @param robots - list of available robots
	 */
	public void registerRobots(List<Robot> robots);
	
	/**
	 * Whenever a solar panel failure is detected this method is called to inform the strategy of the job.
	 * The Strategy needs to make sure to at some point send robots to this job.
	 * There is no requirement to the order of when the jobs are completed 
	 * so if the robot best suited for this job is busy you may save this job and assign robots at a later stage.
	 * 
	 * Store job and execute if it is appropriate (i.e. there are robots available and in a good position to do the job).
	 * 
	 * @param job to be executed
	 */
	public void registerNewJob(Job job);

	/**
	 * Whenever a job is completed this method is called. It assures that all robots used for this job is free
	 * and the strategy now can use these robots for another job.
	 * 
	 * @param job - the job that was completed
	 */
	public void registerJobAsFulfilled(Job job, List<Robot> robots);

	/**
	 * This method is called by the system when other events occur.
	 * A notable example is when a Robot has been issued the move command and reached a target, but not completed any job.
	 */
	public default void updateOrders(){};

}
