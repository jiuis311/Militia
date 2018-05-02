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
		this.setMonsterImage(new DrawTile("/monster3.png"));
		shield = 1;
		shieldImage = new DrawTile("/shield.png");
	}
	@Override
	public void draw(Graphics g) {
		Position pos = this.getCurPosition();
		DrawTile monster = this.getMonsterImage();
		monster.setX(pos.getX()+1);
		monster.setY(pos.getY()+1);
		monster.draw(g);
		if (this.shield == 1) {
			this.shieldImage.setX(pos.getX()+1);
			this.shieldImage.setY(pos.getY()+1);
			this.shieldImage.draw(g);
		}
	}
}
