package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.interfaces.Visitor;

/**
 * Kolize, která vznika po zasazeni nepritele strelou.
 */
public class Collision extends GameObject {
    
    protected int livingTime;

    public Collision(int x, int y) {
        super(x, y);
        livingTime = config.getIntProperty("collision.living_time");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    /** Zkracuje zivot kolize. */
    public void move() {
	livingTime--;
    }
    
    public boolean isDead() {
        return livingTime <= 0;
    }
    
    public void setLivingTime(int livingTime) {
        this.livingTime = livingTime;
    }
    
    /** Kopirovani objektu pro navrhovy vzor Memento. */
    public Collision copy() {
        Collision collision = new Collision(x, y);
        collision.setLivingTime(livingTime);
        return collision;
    }
   
}
