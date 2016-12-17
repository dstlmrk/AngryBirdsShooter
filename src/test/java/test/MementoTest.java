package test;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.memento.Memento;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Testuje ulozeni a obnoveni stavu z mementa.
 */
public class MementoTest {
    
    @Test
    public void mementoTest() {
        Model model = new Model();
        // create random init score
        int initScore = (int)Math.random();
        for (int i = 0; i < initScore; i++) {
            model.getGameStats().increaseScore();
        }
        // test random init score
        assertEquals(initScore, model.getGameStats().getScore());
        // save score
        Memento memento = model.saveStateToMemento();
        // add score
        model.getGameStats().increaseScore();
        // test new score
        assertEquals(initScore + 1, model.getGameStats().getScore());
        // restore old score
        model.restoreStateFromMemento(memento);
        // test restored score
        assertEquals(initScore, model.getGameStats().getScore());
    }

}
