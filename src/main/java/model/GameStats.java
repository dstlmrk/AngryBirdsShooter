package model;

import interfaces.Visitor;

/**
 * Objekt, ktery zaznamenava prubezne skore.
 */
public class GameStats extends GameObject {
    
    Model model;
    int score;
    
    public GameStats(int x, int y, Model model) {
        super(x, y);
        this.model = model;
        score = 0;
    }
    
    public void increaseScore() {
        score++;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    /* Kopirovani objektu pro navrhovy vzor Memento */
    public GameStats copy() {
        GameStats gameStats = new GameStats(x, y, model);
        gameStats.setScore(score);
        return gameStats;
    }
    
    @Override
    public String toString() {
            final StringBuilder sb = new StringBuilder();
            Cannon cannon = model.getCannon();
            String gameMode = config.getProperty("game.mode");
            sb.append("SCORE: ").append(score);
            sb.append(",");
            sb.append(" ANGLE: ").append(cannon.getAngle());
            sb.append(",");
            sb.append(" FORCE: ").append(cannon.getForce());
            sb.append(",");
            sb.append(" GRAVITY: ").append(model.getGravity());
            sb.append(",");
            sb.append(" SHOOTING: ").append(cannon.getCannonShootingMode());
            sb.append(",");
            sb.append(" GAME-MODE: ").append(gameMode);
            return sb.toString();
    }
    
}
