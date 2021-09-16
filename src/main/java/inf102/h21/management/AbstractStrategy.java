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
	/**
	 * List of jobs not yet executed
	 */
	protected Queue<Job> backLog;
	
	public AbstractStrategy() {
		backLog = new LinkedList<Job>();
	}
	
	@Override
	public void registerRobots(List<Robot> robots) {
		this.robots = new ArrayList<Robot>(robots); 
	}

	@Override
	public void registerNewJob(Job job) {
		backLog.add(job);		
		doJobs();
	}
	
	@Override
	public void registerJobAsFulfilled(Job job) {
		doJobs();
	}

	/**
	 * Finds jobs in backLog and assigns robots
	 */
	protected void doJobs() {
		List<Robot> free = getAvailableRobots();
		
		while (!backLog.isEmpty()) {
			Job job = selectJob();
				
			List<Robot> selected = selectRobots(job, free); 
			if(assignRobots(selected, job)) 
				removeJob(job);
			else
				break;
		}
		if(backLog.isEmpty())
			moveFreeRobots();
	}
	
	/**
	 * Selects a Job from the list of available jobs
	 * Currently selects the job at the top of the list
	 * 
	 * @return most appropriate job
	 */
	protected Job selectJob() {
		return backLog.peek();
	}

	protected void removeJob(Job job) { 
		if(backLog.peek().equals(job))
			backLog.poll();
		else
			backLog.remove(job);
	}

	/**
	 * Select robots for the job. Should select robots most appropriate for the job.
	 * 
	 * @param job - The job to select robots for
	 * @param available - The Robots to select among
	 * @return a list of selected robots
	 */
	protected abstract List<Robot> selectRobots(Job job, List<Robot> available);

	/**
	 * When a Robot is not assigned to a Job it is just waiting
	 * We can then position these robots such that when a new job comes in
	 * the robots are already close to the job.
	 */
	protected void moveFreeRobots() {
		// TODO: This method could be suited for task 3
	}
	
	/**
	 * Sends the selected Robots to their Job
	 * 
	 * @return true if robots assigned to job, false if not
	 */
	boolean assignRobots(List<Robot> selected, Job job) { 
		boolean canDo = selected.size() == job.robotsNeeded;
			
		for(Robot r : selected) {
			if(r.isBusy())
				canDo = false;
		}
		if(canDo) {
			for(Robot robot : selected) {
				robot.move(job);
			}
		}
		else {
			for(Robot r : selected) {
				if(!r.isBusy()) {
					r.move(job.location);
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
	public List<Robot> getAvailableRobots(){
		ArrayList<Robot> free = new ArrayList<Robot>(); 
		for(Robot robot : robots) {
			if(!robot.isBusy())
				free.add(robot);
		}
		return free;
	}

}
