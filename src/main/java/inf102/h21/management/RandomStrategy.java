package inf102.h21.management;

import java.util.List;

public class RandomStrategy extends AbstractStrategy {

	@Override
	protected List<Robot> selectRobots(Job job) {
		return null;
	}

	@Override
	public String getName() {
		return "Random strategy";
	}
}
