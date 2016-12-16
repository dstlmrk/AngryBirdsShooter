package model;

import interfaces.Visitor;

/**
 * Nepřítel.
 */
public abstract class Enemy extends GameObject {
    
    protected int livingTime;
    private int type;
    
    public Enemy(int x, int y) {
        super(x, y);
        // random mezi 0 a 1        
        this.type = (int)(Math.random()*2);
        livingTime = config.getIntProperty("enemies.living_time");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public int getType() {
        return type;
    }
    
    public abstract void move();
    public abstract boolean isDead();
}
