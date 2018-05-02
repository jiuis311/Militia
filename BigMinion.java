import java.awt.Graphics;

public class BigMinion extends Minion{
	private int shield;
	private DrawTile shieldImage;
	public int getShield() {
		return shield;
	}
	public void lowerShield() {
		shield = 0;
	}
	BigMinion(Position pos) {
		super(pos);
		shield = 1;
	}
}
