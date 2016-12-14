/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.modes;

import cz.fit.dpo.mvcshooter.Config;
import model.Enemy;

/**
 *
 * @author marek
 */
public class SimpleEnemy extends Enemy {
    
    protected int livingTime;
    Config config;
    
    public SimpleEnemy(int x, int y) {
	super(x, y);
        config = Config.getInstance();
        livingTime = config.getIntProperty("enemies.living_time");
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


//public class SimpleEnemy extends Enemy {
//
//	protected int remainingTime = ModelConfig.ENEMY_LIVE_TIME;
//
//	public SimpleEnemy(int x, int y) {
//		super(x, y);
//	}
//
//	public void setRemainingTime(int remainingTime) {
//		this.remainingTime = remainingTime;
//	}
//
//
//	@Override
//	public boolean isVisible() {
//		return remainingTime >= 0;
//	}
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