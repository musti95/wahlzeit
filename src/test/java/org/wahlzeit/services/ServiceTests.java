package org.wahlzeit.services;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		EmailServiceSuite.class,
		LogBuilderTest.class,
		PhotoManagerTest.class
})
public class ServiceTests {
}
