package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.Config;
import cz.fit.dpo.mvcshooter.model.interfaces.Visitable;
import cz.fit.dpo.mvcshooter.model.interfaces.Visitor;

/**
 * Absraktni trida definujici herni objekt.
 */
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
    
    /** Prijima navstevnika a vyuziva jeho implementaci. */
    @Override
    public abstract void accept(Visitor visitor);
    
    /** Vraci true/false, zda se dva objekty srazily. */
    public boolean collidesWith(GameObject anotherObject) {
        int collisionMargin = config.getIntProperty("collision.margin");
        return (
            Math.abs(x - anotherObject.x) < collisionMargin 
            && Math.abs(y - anotherObject.y) < collisionMargin
        );
    }
    
    /** Porovnani dvou ruznych objektu, zda jsou stejne. */
    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        } else if (anotherObject == null || getClass() != anotherObject.getClass()) {
            return false;
        }
        
        GameObject gameObject = (GameObject) anotherObject;
        if (x != gameObject.x || y != gameObject.y) {
            return false;
        }
        
        return true;
    }
    
}
