package org.wahlzeit.model.coordinate;


import java.lang.Math;

/**
 * A coordinate represents a three dimensional cartesian coordinate.
 */
public class CartesianCoordinate implements Coordinate {
	private static final double EPS = 1E-6;

	private double x;
	private double y;
	private double z;

    CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

	private static boolean doubleEqual(double a, double b) {
		return Math.abs(a - b) < EPS;
	}

	private static double squaredDifference(double a, double b) {
		return (a - b) * (a - b);
	}

	public double getDistance(CartesianCoordinate c) {
		return Math.sqrt(squaredDifference(x, c.x) + squaredDifference(y, c.y) + squaredDifference(z, c.z));
	}

	public boolean isEqual(CartesianCoordinate c) {
		return doubleEqual(x, c.x) && doubleEqual(y, c.y) && doubleEqual(z, c.z);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CartesianCoordinate) {
			return isEqual((CartesianCoordinate) obj);
		}
		return false;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

	@Override
	public CartesianCoordinate asCartesian() {
		return null;
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		return 0;
	}

	@Override
	public SphericCoordinate asSpheric() {
		return ;
	}

	@Override
	public double getCentralAngle(Coordinate coordinate) {
		return 0;
	}

	@Override
	public boolean isEqual(Coordinate coordinate) {
		return false;
	}
}
