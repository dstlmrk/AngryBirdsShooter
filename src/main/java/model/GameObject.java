package model;

import cz.fit.dpo.mvcshooter.Config;
import interfaces.Visitable;
import interfaces.Visitor;

public abstract class GameObject implements Visitable {
    
    protected int x, y;
    Config config;
    
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        config = Config.getInstance();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public abstract void accept(Visitor visitor);
    
    public boolean collidesWith(GameObject anotherObject) {
        int collisionMargin = config.getIntProperty("collision.margin");
        return (
            Math.abs(x - anotherObject.x) < collisionMargin 
            && Math.abs(y - anotherObject.y) < collisionMargin
        );
    }
    
}
