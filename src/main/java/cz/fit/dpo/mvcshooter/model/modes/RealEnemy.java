package cz.fit.dpo.mvcshooter.model.modes;

import cz.fit.dpo.mvcshooter.model.Enemy;

/**
 * Reprezentuje pohybliveho nepritele.
 */
public class RealEnemy extends Enemy {
    
    public RealEnemy(int x, int y) {
	super(x, 0);
    }
    
    /** Implementuje trivialni pohyb smerem dolu. */
    @Override
    public void move() {
        y += 1;
        livingTime--;
    }

    @Override
    public boolean isDead() {
        return livingTime <= 0;
    }
    
    /** Kopirovani objektu pro navrhovy vzor Memento. */
    @Override
    public Enemy copy() {
        RealEnemy realEnemy = new RealEnemy(x, y);
        realEnemy.setLivingTime(livingTime);
        realEnemy.setType(type);
        return realEnemy;
    }
    
    @Override
    public String toString() {
        return "RealEnemy: x type=" + type + ", livingTime=" + livingTime;
    }

}
