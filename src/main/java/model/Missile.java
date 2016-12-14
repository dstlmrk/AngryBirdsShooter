/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cz.fit.dpo.mvcshooter.Config;
import interfaces.MovementStrategy;
import interfaces.Visitor;

/**
 *
 * @author marek    
 */
public class Missile extends GameObject {
    
    private MovementStrategy movementStrategy;
    private int initX, initY, angle, force, time = 1;
    Config config;
    
    public Missile(int x, int y, int angle, int force) {
        super(x, y);
        config = Config.getInstance();
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
    
    public void move() {
        time += 1;
//        TODO: co tady znamena ta petka? = gravity
        Coordinates coordinates = movementStrategy.move(5, this);
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
    
}
