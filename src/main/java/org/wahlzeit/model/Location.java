package org.wahlzeit.model;

import org.wahlzeit.model.coordinate.CartesianCoordinate;

/**
 * A location represents the place, where a {@link Photo} has been taken.
 */
public class Location {
	private CartesianCoordinate coordinate;
	private String name;

	Location(CartesianCoordinate coordinate, String name) {
		this.coordinate = coordinate;
		this.name = name;
	}

	public CartesianCoordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(CartesianCoordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
