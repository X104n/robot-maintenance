package inf102.h21.management;

import java.util.List;

public class BetterStrategy extends AbstractStrategy {

	@Override
	protected List<Robot> selectRobots(Job job) {
		return null;
	}

	@Override
	public String getName() {
		return "Better strategy";
	}

}
