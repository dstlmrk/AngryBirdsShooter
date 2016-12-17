package test;

import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.modes.SimpleEnemy;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testuje metodu Model.getGameObjects pomoci mockovani.
 */
public class GameObjectsTest {
    
    @Test
    public void test() {
        Model model = mock(Model.class);
        
        ArrayList<Missile> missiles = new ArrayList<Missile>();
        for (int i = 0; i < 12; i++) {
            Missile missile = mock(Missile.class);
            missiles.add(missile);
        }
        when(model.getMissiles()).thenReturn(missiles);
        
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int i = 0; i < 11; i++) {
            Enemy enemy = new SimpleEnemy(0, 0);
            enemies.add(enemy);
        }
        when(model.getEnemies()).thenReturn(enemies);
        
        // zavola puvodni metodu
        when(model.getGameObjects()).thenCallRealMethod();
        // assert (+2 protoze cannon a gameStats)
        assertEquals(25, model.getGameObjects().size());
        
    }
}
