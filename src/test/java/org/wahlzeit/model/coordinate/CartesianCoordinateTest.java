package org.wahlzeit.model.coordinate;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testclass for {@link CartesianCoordinate}.
 */

public class CartesianCoordinateTest extends AbstractCoordinateTest {
	@Override
	public void createCoordinates() {
		this.origin = CartesianCoordinate.getInstance(0.0, 0.0, 0.0);
		this.c1 = CartesianCoordinate.getInstance(-1.0, 0.0, 0.0);
		this.c2 = CartesianCoordinate.getInstance(1.0/ Math.sqrt(2), 0.0, 1.0/ Math.sqrt(2));
		this.c3 = CartesianCoordinate.getInstance(1.0/ Math.sqrt(2), 0.0, 1.0/ Math.sqrt(2));
	}

	@Override
	public void testValueObjectOriginSame() {
		Assert.assertSame(origin, CartesianCoordinate.getInstance(0,0,0));
	}

	@Override
	public void testValueObjectCoordinateSame() {
		Assert.assertSame(c2, CartesianCoordinate.getInstance(1.0 / Math.sqrt(2), 0.0, Math.sqrt(2) / 2.0));
	}
}
