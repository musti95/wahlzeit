package org.wahlzeit.model.coordinate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.wahlzeit.model.coordinate.BasicCoordinate.EPS;

public abstract class AbstractCoordinateTest {

	Coordinate origin;
	Coordinate c1;
	Coordinate c2;
	Coordinate c3;

	/**
	 * Hook for creating coordinates of different classes
	 *
	 * @methodtype hook
	 */
	@Before
	public abstract void createCoordinates();
	// 0 0 0
	// -1 0 0
	// 1/sqrt(2) 0 1/sqrt(2)
	// 1/sqrt(2) 0 1/sqrt(2)

	@Test
	public void testAsSpheric() {
		SphericCoordinate origin_spheric = origin.asSpheric();
		SphericCoordinate c1_spheric = c1.asSpheric();
		Assert.assertEquals(0.0, origin_spheric.getPhi(), EPS);
		Assert.assertEquals(0.0, origin_spheric.getTheta(), EPS);
		Assert.assertEquals(0.0, origin_spheric.getRadius(), EPS);

		Assert.assertEquals(Math.PI, c1_spheric.getPhi(), EPS);
		Assert.assertEquals(Math.PI / 2, c1_spheric.getTheta(), EPS);
		Assert.assertEquals(1.0, c1_spheric.getRadius(), EPS);
	}

	@Test
	public void testAsCartesian() {
		CartesianCoordinate origin_cart = origin.asCartesian();
		CartesianCoordinate c1_cart = c1.asCartesian();

		Assert.assertEquals(0.0, origin_cart.getX(), EPS);
		Assert.assertEquals(0.0, origin_cart.getY(), EPS);
		Assert.assertEquals(0.0, origin_cart.getZ(), EPS);

		Assert.assertEquals(-1.0, c1_cart.getX(), EPS);
		Assert.assertEquals(0.0, c1_cart.getY(), EPS);
		Assert.assertEquals(0.0, c1_cart.getZ(), EPS);
	}

	@Test
	public void testIsEqual() {
		Assert.assertTrue(origin.isEqual(CartesianCoordinate.getInstance(0.0, 0.0, 0.0)));
		Assert.assertTrue(c3.isEqual(CartesianCoordinate.getInstance(1.0 / Math.sqrt(2), 0.0, 1.0 / Math.sqrt(2))));

		Assert.assertTrue(origin.isEqual(SphericCoordinate.getInstance(0.0, 0.0, 0.0)));
		Assert.assertTrue(c3.isEqual(SphericCoordinate.getInstance(1.0, Math.PI / 4, 0.0)));

		Assert.assertFalse(origin.isEqual(CartesianCoordinate.getInstance(-1.0, 0.0, 0.0)));
		Assert.assertFalse(c1.isEqual(CartesianCoordinate.getInstance(0.0, 0.0, 0.0)));

		Assert.assertFalse(origin.isEqual(SphericCoordinate.getInstance(1.0, Math.PI / 2, Math.PI)));
		Assert.assertFalse(c1.isEqual(SphericCoordinate.getInstance(0.0, 0.0, 0.0)));
	}

	@Test
	public void testEquals() {
		Assert.assertEquals(origin, origin);
		Assert.assertEquals(c3, c2);

		Assert.assertEquals(c2, SphericCoordinate.getInstance(1.0, Math.PI / 4, 0.0));
		Assert.assertEquals(c2, CartesianCoordinate.getInstance(1.0 / Math.sqrt(2), 0.0, 1.0 / Math.sqrt(2)));

		Assert.assertNotEquals(origin, new Object());
		Assert.assertNotEquals(origin, c1);
	}

	@Test
	public void testGetDistance() {
		Assert.assertEquals(1.0, origin.getCartesianDistance(c1), EPS);
		Assert.assertEquals(0.0, origin.getCartesianDistance(origin), EPS);
		Assert.assertEquals(0.0, c3.getCartesianDistance(c2), EPS);
		Assert.assertEquals(Math.sqrt(2 + Math.sqrt(2)), c1.getCartesianDistance(c2), EPS);
	}

	@Test
	public void testGetCentralAngle() {
		Assert.assertEquals(0.0, c1.getCentralAngle(c1), EPS);
		Assert.assertEquals(3 * Math.PI / 4, c1.getCentralAngle(c2), EPS);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testinvalidGetCentralAngle() {
		Assert.assertEquals(Math.PI, origin.getCentralAngle(c1), EPS);
	}

	@Test
	public abstract void testValueObjectOriginSame();

	@Test
	public abstract void testValueObjectCoordinateSame();
}
