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

public interface Coordinate {
	/**
	 * Threshold for the equality of two double values.
	 */
	double EPS = 1E-6;

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
	 * Interpret coordinate as cartesian.
	 *
	 * @return {@link CartesianCoordinate} representation
	 */
	CartesianCoordinate asCartesian();

	/**
	 * Calculate the cartesian distance between two coordinates.
	 *
	 * @param coordinate Second coordinate
	 * @return Cartesian distance between this and the other coordinate
	 */
	double getCartesianDistance(Coordinate coordinate);

	/**
	 * Interpret coordinate as spherical.
	 *
	 * @return {@link SphericCoordinate} representation
	 */
	SphericCoordinate asSpheric();

	/**
	 * Calculate the central angle between two coordinates and the origin.
	 *
	 * @param coordinate Second coordinate
	 * @return Central angle between this coordinate the origin and the second coordinate.
	 */
	double getCentralAngle(Coordinate coordinate);

	/**
	 * Check if two coordinates are equal using their values.
	 *
	 * @param coordinate Second coordinate
	 * @return true if the coordinates describe (almost) the same 3d point
	 */
	boolean isEqual(Coordinate coordinate);
}
