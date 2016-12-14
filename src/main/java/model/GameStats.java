package model;

import cz.fit.dpo.mvcshooter.Config;
import interfaces.Visitor;

/**
 * Objekt, ktery zaznamenava prubezne skore.
 */
public class GameStats extends GameObject {

    public GameStats(int x, int y) {
        super(x, y);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
//	@Override
//	public void accept(IVisitor visitor) {
//		visitor.visit(this);
//	}
//
//	@Override
//	public String toString() {
//		final StringBuilder sb = new StringBuilder();
//		sb.append("Dosažené skóre: ").append(score);
//		sb.append(",");
//		sb.append(" čas: " + model.getTimeString());
//		sb.append(",");
//		sb.append(" úhel: " + model.getCannon().getAngle());
//		sb.append(",");
//		sb.append(" síla: " + model.getCannon().getForce());
//		sb.append(",");
//		sb.append(" stav kanonu: " + model.getCannonStateString());
//		return sb.toString();
//	}
//}