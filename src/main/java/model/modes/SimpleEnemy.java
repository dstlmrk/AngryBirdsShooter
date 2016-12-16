package model.modes;

import model.Enemy;

/**
 * Představuje jednoduchého nepřítele, který je statický.
 * @author marek
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

}


//
//	@Override
//	public Enemy copy() {
//		SimpleEnemy se = new SimpleEnemy(x, y);
//		se.setTime(time);
//		se.setType(type);
//		se.setRemainingTime(remainingTime);
//		return se;
//	}
//
//}