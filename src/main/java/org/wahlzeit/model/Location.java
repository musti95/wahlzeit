package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Ignore;
import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.model.coordinate.Coordinate;

/**
 * A location represents the place, where a {@link Photo} has been taken.
 */
public class Location {
    @Ignore //coordinate can't be persisted, as it cannot have a no-arg constructor ...
    private CartesianCoordinate coordinate;

    private String name;

    private Location() {
        // for objectify
        coordinate = new CartesianCoordinate(0, 0, 0);
    }

    public Location(Coordinate coordinate, String name) {
        this.coordinate = coordinate.asCartesian();
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate.asCartesian();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
