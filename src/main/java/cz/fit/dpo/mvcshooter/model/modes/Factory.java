package cz.fit.dpo.mvcshooter.model.modes;

import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.Missile;

/**
 * Vzor AbstractFactory - zakladni trida, ktera definuje operace
 * jednotlivych tovaren.
 */
public interface Factory {
    public Enemy createEnemy(int x, int y);
    public Missile createMissile(int initX, int initY, int angle, int force);
}
