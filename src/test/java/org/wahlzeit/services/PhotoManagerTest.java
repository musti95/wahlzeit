package org.wahlzeit.services;

import com.google.appengine.api.images.ImagesServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cache.AsyncCacheFilter;
import com.googlecode.objectify.util.Closeable;
import org.junit.*;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.*;
import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.testEnvironmentProvider.*;

import java.nio.file.Files;
import java.nio.file.Paths;


public class PhotoManagerTest {
    private Closeable session;
    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @BeforeClass
    public static void startObjectify(){
        ImageStorage.setInstance(new DatastoreAdapter());
    }

    @Before
    public void setUp() {
        this.session = ObjectifyService.begin();
    }

    @After
    public void tearDown() {
        AsyncCacheFilter.complete();
        this.session.close();
    }

    @Test
    public void testSaveAndLoadGuitaristPhoto() throws Exception {
        PhotoManager pm = PhotoManager.getInstance();
        String photoPath = "src/main/resources/pictures/satriani.jpg";
        Photo test = pm.createPhoto("guitarist", ImagesServiceFactory.makeImage(Files.readAllBytes(Paths.get(photoPath))), new Location(new CartesianCoordinate(1, 1, 1), "Test"));
        Assert.assertTrue(pm.hasPhoto(test.getId()));
        Assert.assertTrue(pm.getPhoto(test.getId()) instanceof GuitaristPhoto);

        User testUser = new User("testUser", "testUser", "test@test.de");
        testUser.addPhoto(test);
        pm.savePhoto(test);

        pm.getPhotoCache().clear();
        Assert.assertFalse(pm.hasPhoto(test.getId()));

        pm.loadPhotos();
        Assert.assertTrue(pm.hasPhoto(test.getId()));
        Assert.assertTrue(pm.getPhoto(test.getId()) instanceof GuitaristPhoto);

        Location loc = pm.getPhoto(test.getId()).getLocation();
        Assert.assertEquals(new CartesianCoordinate(1, 1, 1), loc.getCoordinate());
        Assert.assertEquals("Test", loc.getName());
    }
}
