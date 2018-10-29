package org.wahlzeit.model;


import java.lang.Math;

/**
 * A coordinate represents a three dimensional cartesian coordinate.
 */
public class Coordinate {
	private static final double EPS = 1E-6;

	private double x;
	private double y;
	private double z;

    Coordinate(double x, double y, double z) {
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

	public double getDistance(Coordinate c) {
		return Math.sqrt(squaredDifference(x, c.x) + squaredDifference(y, c.y) + squaredDifference(z, c.z));
	}

	public boolean isEqual(Coordinate c) {
		return doubleEqual(x, c.x) && doubleEqual(y, c.y) && doubleEqual(z, c.z);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			return isEqual((Coordinate) obj);
		}
		return false;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
