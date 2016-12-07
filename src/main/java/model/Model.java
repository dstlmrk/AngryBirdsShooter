package model;

import interfaces.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import interfaces.Observer;

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
    private Timer timer;
    private List<Observer> observers;
    
    public Model() {
        cannon = new Cannon();
        missiles = new ArrayList<Missile>();
        observers = new ArrayList<Observer>();
        initTimer();
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
        missiles.add(new Missile(cannon.x, cannon.y));
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
        }, 0, 5000);
        
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
//        TODO: vytvorit noveho nepritele
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
}
