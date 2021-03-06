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

import com.googlecode.objectify.annotation.Subclass;

import java.util.HashMap;
import java.util.Map;

/**
 * A three dimensional point represented in spherical coordinates.
 */
public final class SphericCoordinate extends BasicCoordinate {
	private static Map<Integer, SphericCoordinate> cache = new HashMap<>();

	/**
	 * Check that the provided arguments can be used to construct a valid spherical coordinate.
	 *
	 * @param radius Radius r
	 * @param theta  Inclination Theta
	 * @param phi    Azimuth Phi
	 * @throws IllegalArgumentException Arguments are not valid
	 */
	private static void assertValidCoordinateValues(double radius, double theta, double phi) throws IllegalArgumentException {
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
	 * Assert that the coordinate is a valid spherical coordinate.
	 */
	private void assertValidCoordinate() {
		assert radius >= -EPS;
		assert theta >= -EPS && theta < Math.PI + EPS;
		assert phi >= -EPS && phi < 2 * Math.PI + EPS;
	}

	/**
	 * Get a 3D-Point in space represented in spherical coordinates.
	 *
	 * @param radius Radius >= 0
	 * @param theta  Inclination in [0, pi]
	 * @param phi    Azimuth in [0, 2pi]
	 */
	public static SphericCoordinate getInstance(double radius, double theta, double phi) throws IllegalArgumentException {
		if (radius == 0.0) {
			theta = 0.0;
			phi = 0.0;
		}

		Integer key = asString(radius, theta, phi).hashCode();
		SphericCoordinate entry = cache.get(key);
		if( entry == null) {
			entry = new SphericCoordinate(radius, theta, phi);
			cache.put(key, entry);
		}
		return entry;
	}

	private static String asString(double radius, double theta, double phi) {
		return String.format("Spheric(%g."+ DEC_PLACE +"%n, %g."+ DEC_PLACE +"%n, %g."+ DEC_PLACE +"%n)" ,radius, theta, phi);
	}

	private final double phi;
	private final double theta;
	private final double radius;

	private CartesianCoordinate cartesian = null;

	/**
	 * 3D-Point represented in spherical coordinates.
	 *
	 * @param radius Radius >= 0
	 * @param theta  Inclination in [0, pi]
	 * @param phi    Azimuth in [0, 2pi]
	 */
	private SphericCoordinate(double radius, double theta, double phi) {
		assertValidCoordinateValues(radius, theta, phi);
		this.radius = radius;
		this.theta = theta;
		this.phi = phi;
		assertClassInvariants();
	}

	@Override
	public CartesianCoordinate asCartesian() {
		assertClassInvariants();
		if (cartesian != null) {
			return cartesian;
		} else {
			double x = radius * Math.sin(theta) * Math.cos(phi);
			double y = radius * Math.sin(theta) * Math.sin(phi);
			double z = radius * Math.cos(theta);
			cartesian = CartesianCoordinate.getInstance(x, y, z);
			assertClassInvariants();
		}
		return cartesian;
	}

	@Override
	public SphericCoordinate asSpheric() {
		return this;
	}

	@Override
	void assertClassInvariants() {
		assertIsNumber(radius);
		assertIsNumber(theta);
		assertIsNumber(phi);
		assertValidCoordinate();
	}

	/**
	 * Calculate the central angle between two coordinates.
	 *
	 * @param c second coordinate
	 * @return central angle between them
	 */
	double doGetCentralAngle(SphericCoordinate c) {
		double lat1 = theta - Math.PI / 2;
		double lat2 = c.theta - Math.PI / 2;
		double dlong = Math.abs(phi - c.phi);
		return Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(dlong));
	}

	@Override
	public String asString() {
		return asString(radius, theta, phi);
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
