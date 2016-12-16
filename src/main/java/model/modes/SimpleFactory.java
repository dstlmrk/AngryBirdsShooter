package model.modes;

import model.Enemy;
import model.Missile;
import model.movement.Simple;

/**
 * Vzor Abstraktní továrna - implementace, která vytváří statické
 * nepřátele a střely s rovným pohybem pohybu.
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
