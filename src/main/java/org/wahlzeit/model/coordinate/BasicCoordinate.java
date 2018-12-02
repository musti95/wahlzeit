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

public abstract class BasicCoordinate implements Coordinate {
	/**
	 * Threshold for the equality of two double values.
	 */
	static final double EPS = 1E-6;

	/**
	 * Compare two double values for (almost) equality
	 *
	 * @param a double value a
	 * @param b double value b
	 * @return true if a is almost equal to b using a threshold EPS
	 */
	static boolean doubleEqual(double a, double b) {
		return Math.abs(a - b) < EPS;
	}

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
	private static void assertNotNull(Coordinate coordinate){
		assert coordinate != null;
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
		CartesianCoordinate origin = new CartesianCoordinate(0.0, 0.0, 0.0);
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
	public double getCartesianDistance(Coordinate coordinate) {
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
	public double getCentralAngle(Coordinate coordinate) {
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

		boolean result = doubleEqual(this.doGetCartesianDistance(coordinate), 0.0);

		assertClassInvariants();
		return result;

	}
}
