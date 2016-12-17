package cz.fit.dpo.mvcshooter.model.modes;

import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.movement.Realistic;

/**
 * Vzor AbstractFactory - implementace, ktera vytvari pohyblive
 * nepratele a strely s balistickou krivkou.
 */
public class RealFactory implements Factory {

    @Override
    public Enemy createEnemy(int x, int y) {
        return new RealEnemy(x, y);
    }

    @Override
    public Missile createMissile(int initX, int initY, int angle, int force) {
        Missile missile = new Missile(initX, initY, angle, force);
        missile.setMovementStrategy(new Realistic());
        return missile;
    }
    
}
