package model;

import interfaces.MovementStrategy;
import interfaces.Visitor;

/**
 *
 * @author marek    
 */
public class Missile extends GameObject {
    
    private MovementStrategy movementStrategy;
    private final int initX, initY, angle, force;
    private int time = 1;
    
    public Missile(int x, int y, int angle, int force) {
        super(x, y);
        this.initX = x;
        this.initY = y;
        this.angle = angle;
	this.force = force;
    }
    
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
    
    public int getInitX() {
        return initX;
    }

    public int getInitY() {
        return initY;
    }

    public int getAngle() {
        return angle;
    }

    public int getForce() {
        return force;
    }
    
    public int getTime() {
        return time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public void move(int gravity) {            
        time++;
        Coordinates coordinates = movementStrategy.move(gravity, this);
        x = coordinates.getX();
        y = coordinates.getY();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
   
    boolean isOut() {
        int maxWidth = config.getIntProperty("canvas.width") + 100;
        int maxHeight = config.getIntProperty("canvas.height") + 100;
        int min = -100;
        if (x < min || x > maxWidth) {
            return true;
        } else return y < min || y > maxHeight;
        
    }
    
    /* Kopirovani objektu pro navrhovy vzor Memento */
    public Missile copy() {
        Missile missile = new Missile(initX, initY, angle, force);
        missile.setTime(time);
        missile.setMovementStrategy(movementStrategy);
        return missile;
    }
    
}
