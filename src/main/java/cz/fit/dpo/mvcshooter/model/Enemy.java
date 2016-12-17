package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.interfaces.Visitor;

/**
 * Enemy.
 */
public abstract class Enemy extends GameObject {
    
    protected int livingTime;
    protected int type;
    
    public Enemy(int x, int y) {
        super(x, y);
        // random mezi 0 a 1        
        this.type = getRandomType();
        livingTime = config.getIntProperty("enemies.living_time");
    }
    
    public int getRandomType() {
        int ret = (int)(Math.random()*2);
        return ret;
        //return (int)(Math.random()*2);
    }
    
    public abstract void move();
    public abstract boolean isDead();
    public abstract Enemy copy();

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public void setLivingTime(int livingTime) {
        this.livingTime = livingTime;
    }

}
