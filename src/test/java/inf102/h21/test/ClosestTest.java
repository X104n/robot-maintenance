package inf102.h21.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import inf102.h21.management.ClosestStrategy;
import inf102.h21.management.IStrategy;
import inf102.h21.management.Job;
import inf102.h21.management.Location;
import inf102.h21.system.Model;

public class ClosestTest {

	IStrategy random;
	
	@Before
	public void setUp() throws Exception {
		random = new ClosestStrategy();
	}
	
	public Model make10Job1RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		for (int i = 0; i < 10; i++) jobs.add(new Job(new Location(i+1, i+1), i, 1e-10*i, 1));
		return new Model(startLocation, jobs, random);
	}
	
	public Model make2Job1RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		jobs.add(new Job(new Location(1, 1), 0, 1d, 1));
		jobs.add(new Job(new Location(2, 2), 1, 10d, 1));
		return new Model(startLocation, jobs, random);		
	}

	public Model make1DoubleJob2RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		startLocation.add(new Location(1, 1));
		jobs.add(new Job(new Location(2, 2), 0, 1d, 2));
		return new Model(startLocation, jobs, random);		
	}
	
	public Model make2DoubleJob2RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 1));
		startLocation.add(new Location(10, 0));
		jobs.add(new Job(new Location(10, 1), 0, 1d, 2));
		jobs.add(new Job(new Location(12, 1), 1, 5d, 2));
		return new Model(startLocation, jobs, random);		
	}
	
	public Model make4Job4RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(1, 1));
		startLocation.add(new Location(1, 2));
		startLocation.add(new Location(2, 1));
		startLocation.add(new Location(2, 2));
		jobs.add(new Job(new Location(0, 0), 0, 1d, 1));
		jobs.add(new Job(new Location(3, 0), 1, 1d, 1));
		jobs.add(new Job(new Location(0, 3), 2, 1d, 1));
		jobs.add(new Job(new Location(3, 3), 3, 1d, 1));
		return new Model(startLocation, jobs, random);		
	}
	
	@Test
	public void testClosestStrategyOn10Job1RobotModel(){
		Model model = make10Job1RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(Math.sqrt(2)*((10)*(11)/2),model.score(),0.001);
	}

	@Test
	public void testClosestStrategyOn2Job1RobotModel(){
		Model model = make2Job1RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(2.828,model.score(),0.001);
	}
	
	@Test
	public void testClosestStrategyOn1DoubleJob2RobotModel(){
		Model model = make1DoubleJob2RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(2.828,model.score(),0.001);
	}

	@Test
	public void testClosestStrategyOn2DoubleJob2RobotModel(){
		Model model = make2DoubleJob2RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(18d,model.score(),0.001);
	}

	@Test
	public void testClosestStrategyOn4Job4RobotModel(){
		Model model = make4Job4RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(4*1.414,model.score(),0.001);
	}
}