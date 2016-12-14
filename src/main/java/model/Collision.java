/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cz.fit.dpo.mvcshooter.Config;
import interfaces.Visitor;

/**
 *
 * @author marek
 */
public class Collision extends GameObject {
    
    private int remainingTime;
    Config config;

    public Collision(int x, int y) {
        super(x, y);
        config = Config.getInstance();
        remainingTime = config.getIntProperty("enemies.collision_time");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public void decreaseRemainingTime() {
	this.remainingTime -= 1;
    }
    
    public boolean isVisible() {
        return this.remainingTime >= 0;
    }
    
    
}

//	public Collision copy() {
//		Collision collision = new Collision(x, y);
//		collision.setRemainingTime(remainingTime);
//		return collision;
//	}
