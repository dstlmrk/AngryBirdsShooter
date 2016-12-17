package cz.fit.dpo.mvcshooter.model.modes;

import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.movement.Simple;

/**
 * Vzor AbstractFactory - implementace, ktera vytvari staticke
 * nepratele a strely s rovnym pohybem pohybu.
 */
public class SimpleFactory implements Factory {

    @Override
    public Enemy createEnemy(int x, int y) {
        return new SimpleEnemy(x, y);
    }

    @Override
    public Missile createMissile(int initX, int initY, int angle, int force) {
        Missile missile = new Missile(initX, initY, angle, force);
        missile.setMovementStrategy(new Simple());
        return missile;
    }
}
