package cz.fit.dpo.mvcshooter.model.memento;

import java.util.ArrayList;
import java.util.List;
import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Collision;
import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.GameStats;
import cz.fit.dpo.mvcshooter.model.Missile;

/**
 * Navrhovy vzor Memento - externalizovana forma vnitrniho stavu Modelu.
 */
public class Memento {
    
    private final List<Missile> missiles = new ArrayList<Missile>();
    private final List<Enemy> enemies = new ArrayList<Enemy>();
    private final List<Collision> collisions = new ArrayList<Collision>();
    private final GameStats gameStats;
    private final Cannon cannon;
    private final int gravity;

    /** Kopirovani vsech dat z modelu. */
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
