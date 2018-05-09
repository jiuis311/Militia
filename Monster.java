import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Monster {
	private Position curPosition;
	private ArrayList<Position> moveArea;
	private boolean target;
	private DrawTile monsterImage;
	
	//Getter and setter
	public Position getCurPosition() {
		return curPosition;
	}
	public DrawTile getMonsterImage() {
		return monsterImage;
	}
	public void setMonsterImage(DrawTile monsterImage) {
		this.monsterImage = monsterImage;
	}
	public void setCurPosition(Position pos) {
		curPosition = pos;
	}
	public ArrayList<Position> getMoveArea() {
		return moveArea;
	}
	public void setMoveArea(ArrayList<Position> pos) {
		moveArea = pos;
	}
	public boolean isTargeted() {
		return target;
	}
	public void setTarget() {
		target = true;
	}
	//
	Monster(Position pos) {
		curPosition = pos;
		moveArea = new ArrayList<Position>();
		target = false;
	}
	public boolean equals(Object obj) {
        if(obj instanceof Monster) 
            return curPosition.equals(((Monster) obj).curPosition);
        return false;
    }
	abstract void calMoveArea(ArrayList<Monster> pos);
	abstract boolean move(ArrayList<Hero> heros, ArrayList<Monster> mons);
}
