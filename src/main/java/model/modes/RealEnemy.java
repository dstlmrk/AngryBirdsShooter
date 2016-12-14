package model.modes;

import model.Enemy;

/**
 * Reprezentuje nepritele (pohybujiciho se).
 */
public class RealEnemy extends Enemy {
    
    public RealEnemy(int x, int y) {
	super(x, 0);
    }
    
    /**
    * Primitivni implementace padajiciho pohybu
    */
    @Override
    public void move() {
//        time++;
//        y = 0 + time;
    }

//    @Override
//    public boolean isVisible() {
//           return y <= ModelConfig.PLAYGROUND_HEIGHT && x <= ModelConfig.PLAYGROUND_WIDTH;
//    }

//    @Override
//    public Enemy copy() {
//           RealEnemy re = new RealEnemy(x, y);
//           re.setTime(time);
//           re.setType(type);
//           return re;
//    }

    @Override
    public boolean isDead() {
        return false;
    }
}
