package org.wahlzeit.model.coordinate;

/*
 * SphericCoordinate
 *
 * Version 1.0
 *
 * Date 19.11.2018
 *
 * Copyright notice AGPLv3
 */

/**
 * A three dimensional point represented in spherical coordinates.
 */
public final class SphericCoordinate implements Coordinate {

	/**
	 * Check that the provided arguments can be used to construct a valid spherical coordinate.
	 *
	 * @param radius Radius r
	 * @param theta  Inclination Theta
	 * @param phi    Azimuth Phi
	 * @throws IllegalArgumentException Arguments are not valid
	 */
	private static void assertValidConstructorArguments(double radius, double theta, double phi) throws IllegalArgumentException {
		if (radius < -EPS) {
			throw new IllegalArgumentException("Radius can't be negative.");
		}
		if (theta < -EPS || theta > Math.PI + EPS) {
			throw new IllegalArgumentException("Theta must be between 0 and pi.");
		}
		if (phi < -EPS || phi > 2 * Math.PI + EPS) {
			throw new IllegalArgumentException("Phi must be between 0 and 2pi.");
		}
	}

	/**
	 * Check that none of the coordinates are the origin.
	 *
	 * @param coords Varags coordinates to be checked
	 * @throws IllegalArgumentException Coordinates are equal
	 */
	private static void assertCoordinatesNotOrigin(Coordinate... coords) throws IllegalArgumentException {
		SphericCoordinate origin = new SphericCoordinate(0.0, 0.0, 0.0);
		for (Coordinate c : coords) {
			if (c.isEqual(origin)) {
				throw new IllegalArgumentException("One of the coordinates is the origin.");
			}
		}
	}

	private final double phi;
	private final double theta;
	private final double radius;

	/**
	 * 3D-Point represented in spherical coordinates.
	 *
	 * @param radius Radius >= 0
	 * @param theta  Inclination in [0, pi]
	 * @param phi    Azimuth in [0, 2pi]
	 */
	SphericCoordinate(double radius, double theta, double phi) {
		assertValidConstructorArguments(radius, theta, phi);
		this.radius = radius;
		this.theta = theta;
		this.phi = phi;
	}

	@Override
	public CartesianCoordinate asCartesian() {
		double x = radius * Math.sin(theta) * Math.cos(phi);
		double y = radius * Math.sin(theta) * Math.sin(phi);
		double z = radius * Math.cos(theta);
		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public SphericCoordinate asSpheric() {
		return this;
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		return asCartesian().getCartesianDistance(coordinate);
	}

	@Override
	public double getCentralAngle(Coordinate coordinate) {
		assertCoordinatesNotOrigin(this, coordinate);
		return doGetCentralAngle(coordinate.asSpheric());
	}

	private double doGetCentralAngle(SphericCoordinate coordinate) {
		double lat1 = theta - Math.PI / 2;
		double lat2 = coordinate.theta - Math.PI / 2;
		double dlong = Math.abs(phi - coordinate.phi);
		return Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(dlong));
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
		return isEqual(coordinate.asSpheric());
	}

	private boolean isEqual(SphericCoordinate c) {
		return Coordinate.doubleEqual(phi, c.phi) && Coordinate.doubleEqual(theta, c.theta)
				&& Coordinate.doubleEqual(radius, c.radius);
	}

	/**
	 * Get the azimuth Phi.
	 *
	 * @return phi
	 */
	public double getPhi() {
		return phi;
	}

	/**
	 * Get the inclination Theta
	 *
	 * @return theta
	 */
	public double getTheta() {
		return theta;
	}

	/**
	 * Get the radius r
	 *
	 * @return r
	 */
	public double getRadius() {
		return radius;
	}
}
