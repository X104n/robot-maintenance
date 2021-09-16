package inf102.h21.system;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import inf102.h21.management.Job;
import inf102.h21.management.Location;

public class RobotInfoTest {

	RobotInfo info;
	Location home;
	Location target;
	Job job;
	int robotID = 7;
	int jobID = 3;
	double jobTime=0.7;
	
	@Before
	public void setup() {
		home = new Location(3.0, 2.5);
		target = new Location(3.0, 1.5);
		info = new RobotInfo(robotID, home);
		job = new Job(target, jobID, jobTime, 1);
	}
	
	@Test
	public void testSetup() {
		assertTrue(info.getEventId()==0);
		assertTrue(info.getAssignedJob()==null);
		assertTrue(info.robotId==robotID);
		assertTrue(info.getLastKnownLocation().equals(home));
	}
	
	@Test
	public void testAssignJob() {
		assertTrue(info.getAssignedJob()==null);
		info.assignJob(job);
		assertTrue(info.getAssignedJob()==job);
		assertTrue(info.getEventId()==1);
	}

	@Test
	public void testUpdatePosition() {
		assertTrue(info.getAssignedJob()==null);
		assertEquals(home, info.getLastKnownLocation());
		info.updatePosition(job.time);
		info.assignJob(job);
		assertEquals(home, info.getLastKnownLocation());
		assertEquals(job,info.getAssignedJob());
		info.updatePosition(job.time+1);
		assertEquals(target, info.getLastKnownLocation());
		assertTrue(info.getAssignedJob()==job);
		assertTrue(info.getEventId()==1);
	}
}
