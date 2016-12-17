package model;

import interfaces.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import interfaces.Observer;
import cz.fit.dpo.mvcshooter.Config;
import java.util.Iterator;
import model.memento.Memento;
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
    
    public List<Enemy> getEnemies() {
        return enemies;
    }
    
    public List<Collision> getCollisions() {
        return collisions;
    }

    public GameStats getGameStats() {
        return gameStats;
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
            for (Iterator<Enemy> ie = enemies.iterator(); ie.hasNext();) {
                Enemy enemy = ie.next();
                synchronized(missiles) {
                    for (Iterator<Missile> im = missiles.iterator(); im.hasNext();) {
                        Missile missile = im.next();
                        if (enemy.collidesWith(missile)) {
                            synchronized(collisions) {
                                collisions.add(
                                        new Collision(enemy.getX(), enemy.getY())
                                );
                            }
                            gameStats.increaseScore();
                            ie.remove();
                            im.remove();
                        }
                    }
                }
            }
        }
    }

    private void moveObjects() {
        // missiles
        synchronized(missiles) {
            for (Iterator<Missile> i = missiles.listIterator(); i.hasNext();) {
                Missile missile = i.next();
                missile.move(gravity);
                if (missile.isOut()) {
                    i.remove();
                }
            }
        }
        // enemies
        synchronized(enemies) {
            for (Iterator<Enemy> i = enemies.listIterator(); i.hasNext();) {
                Enemy enemy = i.next();
                enemy.move();
                if (enemy.isDead()) {
                    i.remove();
                }
            }
        }
        // collisions
        synchronized(collisions) {
            for (Iterator<Collision> i = collisions.listIterator(); i.hasNext();) {
                Collision collision = i.next();
                collision.move();
                if (collision.isDead()) {
                    i.remove();
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
    
    /* Uklada stav modelu. */
    public Memento saveStateToMemento() {
	// debug
        System.out.println("Ukladam hru. (" + this + ")");
        return new Memento(this);
    }
    
    /* Obnovuje stav modelu. */
    public void restoreStateFromMemento(Memento memento) {
        
    }
    
}
