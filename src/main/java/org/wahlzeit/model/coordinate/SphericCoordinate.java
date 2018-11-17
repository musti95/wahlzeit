package org.wahlzeit.model.coordinate;

public class SphericCoordinate implements Coordinate {
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
        return null;
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
