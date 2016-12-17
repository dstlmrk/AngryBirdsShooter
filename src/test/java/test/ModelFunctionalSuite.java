package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Testovani funkcionalit modelu.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    test.ShootingTest.class,
    test.GameObjectsTest.class,
    test.CannonTest.class,
})
public class ModelFunctionalSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
