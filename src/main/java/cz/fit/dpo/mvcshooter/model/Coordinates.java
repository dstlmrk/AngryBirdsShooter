package cz.fit.dpo.mvcshooter.model;

/**
 *  Coordinates.
 */
public class Coordinates {

    	protected int x, y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
