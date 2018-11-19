package org.wahlzeit.model.coordinate;

/**
 * Testclass for {@link SphericCoordinate}.
 */

public class SphericCoordinateTest extends AbstractCoordinateTest {
    @Override
    public void createCoordinates() {
        this.origin = new SphericCoordinate(0.0, 0.0, 0.0);
        this.c1 = new SphericCoordinate(1.0, Math.PI/2, Math.PI);
        this.c2 = new SphericCoordinate(1.0, Math.PI/4, 0.0);
        this.c3 = new SphericCoordinate(1.0, Math.PI/4, 0.0);
    }
}
