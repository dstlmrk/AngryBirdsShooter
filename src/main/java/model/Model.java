package model;

import interfaces.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import interfaces.Observer;
import cz.fit.dpo.mvcshooter.Config;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marek
 */
public class Model implements Observable {
    
    private Cannon cannon;
    private List<Missile> missiles;
    private List<Enemy> enemies;
    private Timer timer;
    private List<Observer> observers;
    Config config;
    
    public Model() {
        cannon = new Cannon();
        missiles = new ArrayList<Missile>();
        enemies = new ArrayList<Enemy>();
        observers = new ArrayList<Observer>();
        config = Config.getInstance();
        initTimer();
    }
    
    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.add(cannon);
//        gameObjects.addAll(getCollisions());
        gameObjects.addAll(missiles);
        gameObjects.addAll(enemies);
//        gameObjects.add(getGameStats());
        return gameObjects;
    }

    public Cannon getCannon() {
        return cannon;
    }

    public List<Missile> getMissiles() {
        return missiles;
    }
   
    public void moveCannonUp() {
        cannon.setY(cannon.getY() - 10);
        notifyObservers();
    }

    public void moveCannonDown() {
        cannon.setY(cannon.getY() + 10);
        notifyObservers();
    }
    
    public void shootMissile() {
        // TODO: posilat dat factory        
        ArrayList<Missile> newMissiles = cannon.shoot();
	missiles.addAll(newMissiles);
	notifyObservers();
    }
    
    private void initTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveObjects();
            }   
        }, 0, 10);
        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                        addNewEnemy();
                }
        }, 0, config.getIntProperty("enemies.exploring"));
        
//        timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                        timeTicks++;
//                }
//        }, 0, 1000);
    }

    private void moveObjects() {
        //TODO: projet vsechny game objects a zavolat move()
        for (Missile missile : missiles) {
            missile.move();
        }
        notifyObservers();
    }
    
    private void addNewEnemy() {
        if (enemies.size() == config.getIntProperty("enemies.count")) {
            return;
        }
//      TODO: prepsat, prvni stovka je tam proto, aby nedoslo ke generovani u praku
        int randomX = (int) (Math.random() * (config.getIntProperty("canvas.width") - 100)) + 100;
	int randomY = (int) (Math.random() * config.getIntProperty("canvas.height"));
        // Vytvareni nepritele na zaklade vzoru AbstractFactory
//        Enemy enemy = factory.createEnemy(randomX, randomY);
        Enemy enemy = new Enemy(randomX, randomY);
        enemies.add(enemy);
        notifyObservers();
    }
     
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void changeShootingMode() {
        // zmeni vnitrni stav cannonu
        cannon.changeShootingMode();
    }
    
}
