package model.memento;

import java.util.ArrayList;
import java.util.List;
import model.Cannon;
import model.Collision;
import model.Model;
import model.Enemy;
import model.GameStats;
import model.Missile;


/**
 * Externalizovana forma vnitrniho stavu Modelu.
 */
public class Memento {
    
    private List<Missile> missiles = new ArrayList<Missile>();
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<Collision> collisions = new ArrayList<Collision>();
    private GameStats gameStats;
    private Cannon cannon;
    private int gravity;

    /* Kopirovani vsech dat z modelu */
    public Memento(Model model) {
        gravity = model.getGravity();
        for (Missile missile : model.getMissiles()) {
            missiles.add(missile.copy());
        }
        for (Enemy enemy : model.getEnemies()) {
            enemies.add(enemy.copy());
        }
        for (Collision collision : model.getCollisions()) {
                this.collisions.add(collision.copy());
        }
        gameStats = model.getGameStats().copy();
        cannon = model.getCannon().copy();
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

    public Cannon getCannon() {
        return cannon;
    }

    public int getGravity() {
        return gravity;
    }
        
}
