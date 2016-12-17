package model.modes;

import model.Enemy;

/**
 * Reprezentuje pohybliveho nepritele.
 */
public class RealEnemy extends Enemy {
    
    public RealEnemy(int x, int y) {
	super(x, 0);
    }
    
    @Override
    public void move() {
        y += 1;
        livingTime--;
    }

    @Override
    public boolean isDead() {
        return livingTime <= 0;
    }
    
    /* Kopirovani objektu pro navrhovy vzor Memento */
    @Override
    public Enemy copy() {
        RealEnemy realEnemy = new RealEnemy(x, y);
        realEnemy.setLivingTime(livingTime);
        realEnemy.setType(type);
        return realEnemy;
    }

}
