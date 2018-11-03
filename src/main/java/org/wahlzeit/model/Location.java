package org.wahlzeit.model;

/**
 * A location represents the place, where a {@link Photo} has been taken.
 */
public class Location {
	private Coordinate coordinate;
	private String name;

	Location(Coordinate coordinate, String name) {
		this.coordinate = coordinate;
		this.name = name;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
