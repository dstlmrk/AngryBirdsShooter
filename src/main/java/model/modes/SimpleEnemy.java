package model.modes;

import model.Enemy;

/**
 * Reprezentuje statickeho nepritele.
 */
public class SimpleEnemy extends Enemy {
    
    public SimpleEnemy(int x, int y) {
	super(x, y);
    }
    
    @Override
    public void move() {
       livingTime--;
    }

    @Override
    public boolean isDead() {
        return livingTime <= 0;
    }

    /* Kopirovani objektu pro navrhovy vzor Memento */
    @Override
    public Enemy copy() {
        SimpleEnemy simpleEnemy = new SimpleEnemy(x, y);
        simpleEnemy.setLivingTime(livingTime);
        simpleEnemy.setType(type);
        return simpleEnemy;
    }

}
