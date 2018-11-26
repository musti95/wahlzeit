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
	protected static final double EPS = 1E-6;

	/**
	 * Compare two double values for (almost) equality
	 *
	 * @param a double value a
	 * @param b double value b
	 * @return true if a is almost equal to b using a threshold EPS
	 */
	protected static boolean doubleEqual(double a, double b) {
		return Math.abs(a - b) < EPS;
	}

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

	/**
	 * Calculate the cartesian distance between two cartesian coordinates.
	 *
	 * @param c1 one of the coordinates
	 * @param c2 the other coordinate
	 * @return distance between them
	 */
	private static double doGetCartesianDistance(CartesianCoordinate c1, CartesianCoordinate c2) {
		return Math.sqrt(squaredDifference(c1.getX(), c2.getX()) + squaredDifference(c1.getY(), c2.getY())
				+ squaredDifference(c1.getZ(), c2.getZ()));
	}

	/**
	 * Calculate the central angle between two coordinates.
	 *
	 * @param c1 one of the coordinates
	 * @param c2 the other coordinate
	 * @return central angle between them
	 */
	private static double doGetCentralAngle(SphericCoordinate c1, SphericCoordinate c2) {
		double lat1 = c1.getTheta() - Math.PI / 2;
		double lat2 = c2.getTheta() - Math.PI / 2;
		double dlong = Math.abs(c1.getPhi() - c2.getPhi());
		return Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(dlong));
	}

	/**
	 * Check if two coordinates are equal.
	 *
	 * @param c1 one of the coordinates
	 * @param c2 the other coordinate
	 * @return true if coordiantes are equal
	 */
	private static boolean isEqual(CartesianCoordinate c1, CartesianCoordinate c2) {
		return doubleEqual(c1.getX(), c2.getX()) && doubleEqual(c1.getY(), c2.getY()) && doubleEqual(c1.getZ(), c2.getZ());
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

	@Override
	public abstract CartesianCoordinate asCartesian();

	@Override
	public abstract SphericCoordinate asSpheric();

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		return doGetCartesianDistance(this.asCartesian(), coordinate.asCartesian());
	}

	@Override
	public double getCentralAngle(Coordinate coordinate) {
		assertCoordinatesNotOrigin(this, coordinate);
		return doGetCentralAngle(this.asSpheric(), coordinate.asSpheric());
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
		return isEqual(this.asCartesian(), coordinate.asCartesian());
	}
}
