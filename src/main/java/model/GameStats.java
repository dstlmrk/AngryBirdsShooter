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


//public class GameStats extends GameObject {
//
////	private Model model;
//	private int score = 0;
//        Config config;
//
//	public GameStats(Model model) {
////            config = Config.getInstance();
//            super(
//                10, 10
////                config.getIntProperty("info.x"),
////                config.getIntProperty("info.y")
//            );
////		this.model = model;
//	}
//
//	public int getScore() {
//		return score;
//	}
//
//	public void addScore() {
//		score += 1;
////                        ModelConfig.HIT_POINTS;
//	}
//
//	public void minusScore() {
//		score -= 1;
//	}
//
////	public GameStats copy() {
////		GameStats gameStats = new GameStats(model);
////		gameStats.score = this.getScore();
////		return gameStats;
////	}
//
