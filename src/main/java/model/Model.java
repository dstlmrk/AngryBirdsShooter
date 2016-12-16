package model;

import interfaces.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import interfaces.Observer;
import cz.fit.dpo.mvcshooter.Config;
import java.util.Iterator;
import model.modes.Factory;
import model.modes.RealFactory;
import model.modes.SimpleFactory;

/**
 *
 * @author marek
 */
public class Model implements Observable {
    
    private Factory factory;
    private Cannon cannon;
    private List<Missile> missiles;
    private List<Enemy> enemies;
    private List<Collision> collisions;
    private Timer timer;
    private List<Observer> observers;
    private GameStats gameStats;
    private int gravity;
    Config config;
    
    public Model() {
        config = Config.getInstance();
        factory = getFactory();
        cannon = new Cannon(
                config.getIntProperty("canonn.x_default"),
                config.getIntProperty("cannon.y_default")
        );
        missiles = new ArrayList<Missile>();
        enemies = new ArrayList<Enemy>();
        collisions = new ArrayList<Collision>();
        observers = new ArrayList<Observer>();
        gameStats = new GameStats(
                config.getIntProperty("info.x"),
                config.getIntProperty("info.y"),
                this
        );
        gravity = config.getIntProperty("gravity.default");
        initTimer();
    }
    
    private Factory getFactory() {
        if (config.getProperty("game.mode").equals("simple")) {
            return new SimpleFactory();
        } else {
            return new RealFactory();
        }
    }
    
    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.add(cannon);
        gameObjects.addAll(collisions);
        gameObjects.addAll(missiles);
        gameObjects.addAll(enemies);
        gameObjects.add(gameStats);
        return gameObjects;
    }

    public Cannon getCannon() {
        return cannon;
    }
    
    public List<Missile> getMissiles() {
        return missiles;
    }
   
    public void moveCannonUp() {
        cannon.moveUp();
        notifyObservers();
    }

    public void moveCannonDown() {
        cannon.moveDown();
        notifyObservers();
    }
    
    public void angleUp() {
        cannon.angleUp();
    }
    
    public void angleDown() {
        cannon.angleDown();
    }
    
    public void forceUp() {
        cannon.forceUp();
    }
    
    public void forceDown() {
        cannon.forceDown();
    }

    public void gravityUp() {
        gravity += config.getIntProperty("gravity.step");
    }

    public void gravityDown() {
        gravity -= config.getIntProperty("gravity.step");
    }

    public int getGravity() {
        return gravity;
    }
    
    public void shootMissile() {
        // TODO: posilat dat factory        
        ArrayList<Missile> newMissiles = cannon.shoot(factory);
        synchronized(missiles) {
            missiles.addAll(newMissiles);
        }
        notifyObservers();
    }
    
    private void initTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveObjects();
            }   
        }, 0, 15);
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
    
    private synchronized void addCollisions() {
        synchronized(enemies) {
            for (Iterator<Enemy> iterEnemy = enemies.iterator(); iterEnemy.hasNext(); ) {
                Enemy enemy = iterEnemy.next();
                synchronized(missiles) {
                    for (Iterator<Missile> iterMissile = missiles.iterator(); iterMissile.hasNext(); ) {
                        Missile missile = iterMissile.next();

                        if (enemy.collidesWith(missile)) {
                            synchronized(collisions) {
                                collisions.add(new Collision(enemy.getX(), enemy.getY()));
                            }
                            gameStats.increaseScore();
                            iterEnemy.remove();
                            iterMissile.remove();
                        }
                    }
                }
            }
        }
    }

    private void moveObjects() {

//        for (Missile missile : missiles) {
//            if (missile.isOut()) {
//                missile.setVisible(false);
//            }
//            missile.move();
//        }
        
        // missiles
        synchronized(missiles) {
            for (Iterator<Missile> iter = missiles.listIterator(); iter.hasNext();) {
                Missile missile = iter.next();
                missile.move(gravity);
                if (missile.isOut()) {
                    iter.remove();
                }
            }
        }
        
        // enemies
        synchronized(enemies) {
            for (Iterator<Enemy> iter = enemies.listIterator(); iter.hasNext();) {
                Enemy enemy = iter.next();
                enemy.move();
                if (enemy.isDead()) {
                    iter.remove();
                }
            }
        }
        
        // collisions
        synchronized(collisions) {
            for (Iterator<Collision> iter = collisions.listIterator(); iter.hasNext();) {
                Collision collision = iter.next();
                collision.move();
                if (collision.isDead()) {
                    iter.remove();
                }
            }
        }
        
        // added new collisions
        addCollisions();
        
        notifyObservers();
    }
    
    private void addNewEnemy() {
        if (enemies.size() == config.getIntProperty("enemies.count")) {
            return;
        }
//      TODO: prepsat, prvni stovka je tam proto, aby nedoslo ke generovani u praku
        int randomX = (int) (Math.random() * (config.getIntProperty("canvas.width") - 100)) + 100;
	int randomY = (int) (Math.random() * config.getIntProperty("canvas.height"));
        
        // AbstractFactory
        Enemy enemy = factory.createEnemy(randomX, randomY);
        synchronized(enemies) {
            enemies.add(enemy);
        }
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
