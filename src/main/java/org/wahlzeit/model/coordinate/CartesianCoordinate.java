package org.wahlzeit.model.coordinate;

/*
 * CartesianCoordinate
 *
 * Version 1.0
 *
 * Date 19.11.2018
 *
 * Copyright notice AGPLv3
 */

import java.lang.Math;

/**
 * A three dimensional point represented in cartesian coordinates.
 */
public final class CartesianCoordinate extends BasicCoordinate {
	private final double x;
	private final double y;
	private final double z;

	/**
	 * 3D-Point represented in cartesian coordinates.
	 *
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param z z-coordinate
	 */
	CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants();
	}

	@Override
	public CartesianCoordinate asCartesian() {
		return this;
	}

	@Override
	public SphericCoordinate asSpheric() {
		assertClassInvariants();
		double r = Math.sqrt(x * x + y * y + z * z);
		double theta = r == 0.0 ? 0.0 : Math.acos(z / r);
		double phi = Math.atan2(y, x);
		assertClassInvariants();
		return new SphericCoordinate(r, theta, phi);
	}

	@Override
	void assertClassInvariants() {
		assertIsNumber(x);
		assertIsNumber(y);
		assertIsNumber(z);
	}

	/**
	 * Calculate the cartesian distance between two cartesian coordinates.
	 *
	 * @param c second coordinate
	 * @return cartesian distance
	 */
	double doGetCartesianDistance(CartesianCoordinate c) {
		return Math.sqrt(squaredDifference(x, c.x) + squaredDifference(y, c.y)
				+ squaredDifference(z, c.z));
	}

	/**
	 * Get the x-coordinate.
	 *
	 * @return x-coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the y-coordinate.
	 *
	 * @return y-coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Get the z-coordinate.
	 *
	 * @return z-coordinate
	 */
	public double getZ() {
		return z;
	}
}
