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

import com.googlecode.objectify.annotation.Subclass;

import java.lang.Math;
import java.util.HashMap;
import java.util.Map;

/**
 * A three dimensional point represented in cartesian coordinates.
 */
public final class CartesianCoordinate extends BasicCoordinate {
	private static Map<Integer, CartesianCoordinate> cache = new HashMap<>();

	/**
	 * Get a 3D-Point in space represented in cartesian coordinates.
	 *
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param z z-coordinate
	 */
	public static CartesianCoordinate getInstance(double x, double y, double z) {
		Integer key = asString(x, y, z).hashCode();
		CartesianCoordinate entry = cache.get(key);
		if( entry == null) {
			entry = new CartesianCoordinate(x, y, z);
			cache.put(key, entry);
		}
		return entry;
	}

	private static String asString(double x, double y, double z) {
		return String.format("Cartesian(d%g."+ DEC_PLACE +"%n, %g."+ DEC_PLACE +"%n, %g."+ DEC_PLACE +"%n)", x, y, z);
	}

	private final double x;
	private final double y;
	private final double z;

	private SphericCoordinate spheric = null;

	/**
	 * 3D-Point represented in cartesian coordinates.
	 *
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param z z-coordinate
	 */
	private CartesianCoordinate(double x, double y, double z) {
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
		if (spheric == null) {
			double r = Math.sqrt(x * x + y * y + z * z);
			double theta = r == 0.0 ? 0.0 : Math.acos(z / r);
			double phi = Math.atan2(y, x);
			spheric = SphericCoordinate.getInstance(r, theta, phi);
			assertClassInvariants();
		}
		return spheric;
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

	@Override
	public String asString() {
	return asString(x, y, z);
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
