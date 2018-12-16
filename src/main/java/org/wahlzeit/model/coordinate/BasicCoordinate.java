package org.wahlzeit.model.coordinate;

/*
 * Coordinate
 *
 * Version 1.0
 *
 * Date 19.11.2018
 *
 * Copyright notice AGPLv3
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BasicCoordinate implements Coordinate {
	/**
	 * Threshold for the equality of two double values.
	 */
	static final int DEC_PLACE = 6;
	static final double EPS = 1*Math.pow(10, -DEC_PLACE);

	/**
	 * Calculate the square of the difference of two values.
	 *
	 * @param a A double value
	 * @param b A double value
	 * @return (a - b)^2
	 */
	static double squaredDifference(double a, double b) {
		return (a - b) * (a - b);
	}

	/**
	 * Assert that a coordinate object is not null.
	 * @param coordinate coordinate
	 */
	private static void assertNotNull(Coordinate coordinate) throws IllegalArgumentException {
		if (coordinate == null) {
			throw new IllegalArgumentException("Coordinate can't be null.");
		}
	}

	/**
	 * Assert that a double value is actually a number.
	 * @param val value
	 */
	static void assertIsNumber(double val){
		assert Double.isFinite(val);
	}

	/**
	 * Check that none of the coordinates are the origin.
	 *
	 * @param coords Varags coordinates to be checked
	 * @throws IllegalArgumentException Coordinates are equal
	 */
	private static void assertCoordinatesNotOrigin(Coordinate... coords) throws IllegalArgumentException {
		CartesianCoordinate origin = CartesianCoordinate.getInstance(0.0, 0.0, 0.0);
		for (Coordinate c : coords) {
			if (c.isEqual(origin)) {
				throw new IllegalArgumentException("One of the coordinates is the origin.");
			}
		}
	}

	/**
	 * Assert that class invariants hold.
	 */
	abstract void assertClassInvariants();

	@Override
	public abstract CartesianCoordinate asCartesian();

	@Override
	public abstract SphericCoordinate asSpheric();

	@Override
	public double getCartesianDistance(Coordinate coordinate) throws IllegalArgumentException {
		assertClassInvariants();
		assertNotNull(coordinate);

		double result = doGetCartesianDistance(coordinate);

		assertIsNumber(result);
		assertClassInvariants();
		return result;
	}

	private double doGetCartesianDistance(Coordinate coordinate) {
		return this.asCartesian().doGetCartesianDistance(coordinate.asCartesian());
	}

	@Override
	public double getCentralAngle(Coordinate coordinate) throws IllegalArgumentException {
		assertClassInvariants();
		assertNotNull(coordinate);
		assertCoordinatesNotOrigin(this, coordinate);

		double result = doGetCentralAngle(coordinate);

		assertIsNumber(result);
		assertClassInvariants();
		return result;
	}

	private double doGetCentralAngle(Coordinate coordinate) {
		return this.asSpheric().doGetCentralAngle(coordinate.asSpheric());
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
		assertClassInvariants();
		assertNotNull(coordinate);

		//boolean result = doubleEqual(this.doGetCartesianDistance(coordinate), 0.0);
		boolean result = asCartesian().hashCode() == coordinate.asCartesian().hashCode();

		assertClassInvariants();
		return result;
	}

	/**
	 * Get a unique string representation of the coordinate.
	 * @return string representation
	 */
	public abstract String asString();

	@Override
	public int hashCode() {
		return asString().hashCode();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Coordinate is a value type and cannot be cloned.");
	}
}
