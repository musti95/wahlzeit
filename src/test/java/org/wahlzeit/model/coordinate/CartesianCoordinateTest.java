package org.wahlzeit.model.coordinate;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

/**
 * Testclass for {@link CartesianCoordinate}.
 */

public class CartesianCoordinateTest {

	private static final double EPS = 1E-10;

	private CartesianCoordinate origin;
	private CartesianCoordinate c1;
	private CartesianCoordinate c2;

	@Before
	public void createCoordinates() {
		origin = new CartesianCoordinate(0, 0, 0);
		c1 = new CartesianCoordinate(1.0, 1.0, 1.0);
		c2 = new CartesianCoordinate(1.0, 1.0, 1.0);
	}

	@Test
	public void testIsEqual() {
		Assert.assertTrue(origin.isEqual(origin));
		Assert.assertTrue(c1.isEqual(c2));
		Assert.assertTrue(c2.isEqual(c1));

		Assert.assertFalse(origin.isEqual(c1));
		Assert.assertFalse(c1.isEqual(origin));
	}

	@Test
	public void testEquals() {
		Assert.assertEquals(origin, origin);
		Assert.assertEquals(c1, c2);

		Assert.assertNotEquals(origin, new Object());
		Assert.assertNotEquals(origin, c1);
	}

	@Test
	public void testGetDistance() {
		Assert.assertEquals(Math.sqrt(3), origin.getDistance(c1), EPS);
		Assert.assertEquals(0.0, origin.getDistance(origin), EPS);
		Assert.assertEquals(0.0, c1.getDistance(c2), EPS);
	}

	@Test
	public void testToString() {
		Assert.assertEquals("(0.0, 0.0, 0.0)", origin.toString());
		Assert.assertEquals("(1.0, 1.0, 1.0)", c1.toString());
	}
}
