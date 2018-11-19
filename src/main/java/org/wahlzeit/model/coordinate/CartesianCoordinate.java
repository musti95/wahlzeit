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
public final class CartesianCoordinate implements Coordinate {

	/**
	 * Calculate the square of the difference of two values.
	 *
	 * @param a A double value
	 * @param b A double value
	 * @return (a - b)^2
	 */
	private static double squaredDifference(double a, double b) {
		return (a - b) * (a - b);
	}

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
	}

	@Override
	public CartesianCoordinate asCartesian() {
		return this;
	}

	@Override
	public SphericCoordinate asSpheric() {
		double r = Math.sqrt(x * x + y * y + z * z);
		double theta = r == 0.0 ? 0.0 : Math.acos(z / r);
		double phi = Math.atan2(y, x);
		return new SphericCoordinate(r, theta, phi);
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		return doGetCartesianDistance(coordinate.asCartesian());
	}

	private double doGetCartesianDistance(CartesianCoordinate c) {
		return Math.sqrt(squaredDifference(x, c.x) + squaredDifference(y, c.y) + squaredDifference(z, c.z));
	}

	@Override
	public double getCentralAngle(Coordinate coordinate) {
		return asSpheric().getCentralAngle(coordinate);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			return isEqual((Coordinate) obj);
		}
		return false;
	}

	@Override
	public boolean isEqual(Coordinate coordinate) {
		return isEqual(coordinate.asCartesian());
	}

	private boolean isEqual(CartesianCoordinate c) {
		return Coordinate.doubleEqual(x, c.x) && Coordinate.doubleEqual(y, c.y) && Coordinate.doubleEqual(z, c.z);
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
