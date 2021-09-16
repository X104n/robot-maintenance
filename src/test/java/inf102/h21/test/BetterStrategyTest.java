package inf102.h21.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import inf102.h21.management.BetterStrategy;
import inf102.h21.management.ClosestStrategy;
import inf102.h21.management.IStrategy;
import inf102.h21.management.Job;
import inf102.h21.management.Location;
import inf102.h21.management.RandomStrategy;
import inf102.h21.system.Model;

/**
 * Tests for which test cases your strategy beats both random and closest
 */
public class BetterStrategyTest {
	IStrategy random = new RandomStrategy();
	IStrategy closest = new ClosestStrategy();
	IStrategy better = new BetterStrategy();
	
	@Before
	public void setUp() throws Exception {
		random = new RandomStrategy();
		closest = new ClosestStrategy();
		better = new BetterStrategy();	
	}
	
	@Test
	public void testBetterOnInput1() throws Exception{
		testStrategy("input/01.in");
	}
	
	@Test
	public void testBetterOnInput2() throws Exception{
		testStrategy("input/02.in");
	}
	
	@Test
	public void testBetterOnInput3() throws Exception{
		testStrategy("input/03.in");
	}

	@Test
	public void testBetterOnInput4() throws Exception{
		testStrategy("input/04.in");
	}
	
	@Test
	public void testBetterOnInput5() throws Exception{
		testStrategy("input/05.in");
	}
	
	@Test
	public void testBetterOnInput6() throws Exception{
		testStrategy("input/06.in");
	}
	
	public void testStrategy(String inputFile) throws Exception{
		Model rmodel = new Model(inputFile, random);
		Model cmodel = new Model(inputFile, closest);
		Model bmodel = new Model(inputFile, better);
		random.registerRobots(rmodel.listRobots());
		closest.registerRobots(cmodel.listRobots());
		better.registerRobots(bmodel.listRobots());
		rmodel.runSimulation();
		cmodel.runSimulation();
		bmodel.runSimulation();
		assertBetterScore(rmodel.score(), cmodel.score(), bmodel.score(),inputFile);
	}

	private void runSimulation() {
		
	}
	
	private void assertBetterScore(double randomScore, double closestScore, double betterScore, String file) {
		assertTrue("random strategy beats best strategy on "+file,randomScore > betterScore);
		assertTrue("closest strategy beats best strategy on "+file,closestScore > betterScore);
	}
	

}