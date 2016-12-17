package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.interfaces.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import cz.fit.dpo.mvcshooter.model.interfaces.Observer;
import cz.fit.dpo.mvcshooter.Config;
import java.util.Iterator;
import cz.fit.dpo.mvcshooter.model.memento.Memento;
import cz.fit.dpo.mvcshooter.model.modes.Factory;
import cz.fit.dpo.mvcshooter.model.modes.RealFactory;
import cz.fit.dpo.mvcshooter.model.modes.SimpleFactory;

/**
 * Model.
 */
public class Model implements Observable {
    
    private final Factory factory;
    private Cannon cannon;
    private List<Missile> missiles;
    private List<Enemy> enemies;
    private List<Collision> collisions;
    private Timer timer;
    private final List<Observer> observers;
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
    
    /** Vraci vsechny herni objekty, ktere je potreba vykreslit. */
    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.add(getCannon());
        gameObjects.addAll(getCollisions());
        gameObjects.addAll(getMissiles());
        gameObjects.addAll(getEnemies());
        gameObjects.add(getGameStats());
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
    
    /** Strili na zaklade factory. */
    public void shootMissile() {
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
    }
    
    /** Detekuje kolize na platne. */
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
    
    /** Pohybuje se vsemi hernimi objekty na platne. */
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
    
    /** Generuje nepritele na nahodne pozici. */
    private void addNewEnemy() {
        if (enemies.size() == config.getIntProperty("enemies.count")) {
            return;
        }
        int canvasWidth = config.getIntProperty("canvas.width");
        int canvasHeight = config.getIntProperty("canvas.height");
        // nechci aby se do blizkosti cannonu generovali nepratele
        int randomX = (int) (Math.random() * (canvasWidth - 100)) + 100;
	int randomY = (int) (Math.random() * canvasHeight);
        
        // AbstractFactory, vytvori simple/realistic nepritele
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
    
    /** Informuje observery. */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /** Meni vnitrni stav cannonu. */
    public void changeShootingMode() {
        cannon.changeShootingMode();
    }
    
    /** Uklada stav modelu. */
    public Memento saveStateToMemento() {
	return new Memento(this);
    }
    
    /** Obnovuje stav modelu. */
    public void restoreStateFromMemento(Memento memento) {
        missiles = new ArrayList<Missile>();
        for (Missile missile : memento.getMissiles()) {
            Missile restoredMissile = missile.copy();
            missiles.add(restoredMissile);
	}
        enemies = new ArrayList<Enemy>();
        for (Enemy enemy : memento.getEnemies()) {
            Enemy restoredEnemy = enemy.copy();
            enemies.add(restoredEnemy);
	}
        collisions = new ArrayList<Collision>();
        for (Collision collision : memento.getCollisions()) {
            Collision restoredCollision = collision.copy();
            collisions.add(restoredCollision);
	} 
        gravity = memento.getGravity();
        cannon = memento.getCannon().copy();
        gameStats = memento.getGameStats().copy();
    }
    
}
