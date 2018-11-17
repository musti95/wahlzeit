package org.wahlzeit.model.coordinate;

public interface Coordinate {
	public CartesianCoordinate asCartesian();
	public double getCartesianDistance(Coordinate coordinate);
	public SphericCoordinate asSpheric();
	public double getCentralAngle(Coordinate coordinate);
	public boolean isEqual(Coordinate coordinate);

}
