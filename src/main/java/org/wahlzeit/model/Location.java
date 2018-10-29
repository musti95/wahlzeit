package org.wahlzeit.model;

/**
 * A location represents the place, where a {@link Photo} has been taken.
 */
public class Location {
	public Coordinate coordinate;
	public String name;

	Location(Coordinate coordinate, String name) {
		this.coordinate = coordinate;
		this.name = name;
	}
}
