package main.entities.characters.monsters;

import main.graphics.DrawTile;
import main.helpers.Position;

public class BigMinion extends Minion{
	private int shield;
	private DrawTile shieldImage;
	public int getShield() {
		return shield;
	}
	public void lowerShield() {
		shield = 0;
	}
	public BigMinion(Position pos) {
		super(pos);
		shield = 1;
	}
}
