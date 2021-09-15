package inf102.h21.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import inf102.h21.management.Location;

class LocationTest {

	@Test
	void testHashCode() {
		Location a = new Location(2.4,7.3);
		Location b = new Location(7.3,2.4);
		Location c = new Location(2.4,7.3);
		// Swapping variables should give different hash values to avoid collisions
		assertFalse(a.hashCode()==b.hashCode());
		// Equal elements should have equal hash code
		assertEquals(a.hashCode(), c.hashCode());
	}

	@Test
	void testLocation() {
		double x = 7.3;
		double y = 2.4;
		Location loc = new Location(x,y);
		assertEquals(x, loc.x);
		assertEquals(y, loc.y);
	}

	@Test
	void testDist() {
		double x = 7.3;
		double y = 2.4;
		double offset = 3.3;
		Location a = new Location(x,y);
		Location b = new Location(x+offset,y);
		Location c = new Location(x,y+offset);
		
		assertEquals(offset, a.dist(b),0.00001);
		assertEquals(offset, a.dist(c),0.00001);
		assertEquals(offset*Math.sqrt(2), b.dist(c),0.00001);

		offset += Integer.MAX_VALUE;
		Location far = new Location(x,y+offset);
		assertEquals(offset, a.dist(far));
}

	@Test
	void testEqualsObject() {
		Location a = new Location(2.4,7.3);
		Location b = new Location(1.7,0.3);
		assertTrue(a.equals(a));
		assertTrue(b.equals(b));
		assertFalse(a.equals(b));
		System.out.println(Math.ulp((double)Integer.MAX_VALUE)*Math.sqrt(100));
	}

}
