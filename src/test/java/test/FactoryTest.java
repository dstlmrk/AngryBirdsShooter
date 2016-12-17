package test;

import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.modes.RealEnemy;
import cz.fit.dpo.mvcshooter.model.modes.RealFactory;
import cz.fit.dpo.mvcshooter.model.modes.SimpleEnemy;
import cz.fit.dpo.mvcshooter.model.modes.SimpleFactory;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testovani simple a real nepratel. Mockovani metody random().
 */
public class FactoryTest {
    
    @Test
    public void testSimpleEnemy() {
        // random vraci nulu, abych mel jednotny typ
        Enemy enemy = mock(Enemy.class);
        when(enemy.getRandomType()).thenReturn(0);
        
        // simple enemy
        SimpleEnemy simpleEnemy = new SimpleEnemy(0, 0);
        // simple enemy by factory
        SimpleFactory simpleFactory = new SimpleFactory();
        Enemy simpleEnemyByFactory = simpleFactory.createEnemy(0, 0);
        // assert
        assertEquals(simpleEnemy, simpleEnemyByFactory);
    }
     
    @Test
    public void testRealEnemy() {
        // random vraci nulu, abych mel jednotny typ
        Enemy enemy = mock(Enemy.class);
        when(enemy.getRandomType()).thenReturn(0);
        
        // realistic enemy
        RealFactory realFactory = new RealFactory();
        // realistic enemy by factory
        RealEnemy realEnemy = new RealEnemy(0, 0);
        Enemy realEnemyByFactory = realFactory.createEnemy(0, 0);
        // assert
        assertEquals(realEnemy, realEnemyByFactory);
    }
    
    @Test
    public void testEnemiesByFactory() {
        // random vraci nulu, abych mel jednotny typ
        Enemy enemy = mock(Enemy.class);
        when(enemy.getRandomType()).thenReturn(0);
        
        // real enemy
        RealFactory realFactory = new RealFactory();
        Enemy realEnemyByFactory = realFactory.createEnemy(0, 0);
        // simple enemy
        SimpleFactory simpleFactory = new SimpleFactory();
        Enemy simpleEnemyByFactory = simpleFactory.createEnemy(0, 0);
        
        // assert
        assertThat(simpleEnemyByFactory, is(not(realEnemyByFactory)));
    }
    
}
