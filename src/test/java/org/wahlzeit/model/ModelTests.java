package org.wahlzeit.model;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.coordinate.CartesianCoordinateTest;
import org.wahlzeit.model.coordinate.SphericCoordinateTest;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ValueTest.class,
		UserStatusTest.class,
		TagsTest.class,
		PhotoFilterTest.class,
		GuestTest.class,
		GenderTest.class,
		FlagReasonTest.class,
		CartesianCoordinateTest.class,
		SphericCoordinateTest.class,
		AccessRightsTest.class,
		DatastoreAdapterTest.class
})
public class ModelTests {
}
