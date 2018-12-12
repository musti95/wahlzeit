package org.wahlzeit.model.coordinate;

/**
 * Testclass for {@link SphericCoordinate}.
 */

public class SphericCoordinateTest extends AbstractCoordinateTest {
    @Override
    public void createCoordinates() {
        this.origin = SphericCoordinate.getInstance(0.0, 0.0, 0.0);
        this.c1 = SphericCoordinate.getInstance(1.0, Math.PI/2, Math.PI);
        this.c2 = SphericCoordinate.getInstance(1.0, Math.PI/4, 0.0);
        this.c3 = SphericCoordinate.getInstance(1.0, Math.PI/4, 0.0);
    }
}
