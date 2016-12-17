package test;

import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.modes.SimpleFactory;
import cz.fit.dpo.mvcshooter.model.shooting.TripleShooting;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Testovani toho, kolik vystreli cannon strel pri ruznych
 * stavech (single, triple).
 */
public class ShootingTest {
    
    @Test
    public void singleTest() {
        SimpleFactory simpleFactory = new SimpleFactory();
        Cannon cannon = new Cannon(0, 0);
        int shotsCount = cannon.shoot(simpleFactory).size();
        assertEquals(1, shotsCount);
    }
    
    @Test
    public void tripleTest() {
        SimpleFactory simpleFactory = new SimpleFactory();
        Cannon cannon = new Cannon(0, 0);
        cannon.setShootingState(new TripleShooting());
        int shotsCount = cannon.shoot(simpleFactory).size();
        assertEquals(3, shotsCount);
    }
}
