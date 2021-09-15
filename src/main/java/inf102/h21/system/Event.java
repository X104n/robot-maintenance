package inf102.h21.system;

/**
 * Events represent times when an action is needed from the Strategy class.
 * An event is either when a task occurs an Job is created for the robots to do,
 * or when a Job is completed. The type of event is described by an EventType.
 * 
 * @author Olav Bakken og Martin Vatshelle
 *
 */
public class Event implements Comparable<Event>{
	public final int robotID, jobID, checkID;
	public final double time;
	public final EventType eventType;
	
	Event(int robotID, int jobID, int checkID, double t, EventType eventType){
		this.robotID = robotID;
		this.jobID = jobID;
		this.checkID = checkID;
		this.time = t;
		this.eventType = eventType;
	}
	
	@Override
	public int compareTo(Event e) {
		int cmp = Double.compare(this.time, e.time);
		if (cmp == 0) {
			return this.eventType.compareTo(e.eventType);
		}
		return cmp;
	}
}
