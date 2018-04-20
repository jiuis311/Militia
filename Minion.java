import java.util.ArrayList;

public class Minion extends Monster {
	Minion(Position pos) {
		super(pos);
	}

	@Override
	public void calculateMoveArea(ArrayList<Monster> pos) {
		moveArea.clear();
		Position tmp = new Position(curPosition.getX()+1,curPosition.getY());
		if (tmp.getX() <= 7) this.moveArea.add(tmp);
		tmp = new Position(curPosition.getX(),curPosition.getY());
		this.moveArea.add(tmp);
		tmp = new Position(curPosition.getX()-1,curPosition.getY());
		if (tmp.getX() >= 0) this.moveArea.add(tmp);
		tmp = new Position(curPosition.getX(),curPosition.getY()+1);
		if (tmp.getY() <= 7) this.moveArea.add(tmp);
		tmp = new Position(curPosition.getX(),curPosition.getY()-1);
		if (tmp.getY() >= 0) this.moveArea.add(tmp);
	}
	
	private int getDistance(Position pos1, Position pos2) {
		return Math.abs(pos2.getX() - pos1.getX()) + Math.abs(pos2.getY() - pos1.getY());
	}
	
	@Override
	public boolean move(Position pos, ArrayList<Monster> mons) {
		calculateMoveArea(mons);
		Position min = curPosition;
		for (Position position: moveArea) {
			if (getDistance(min,pos) > getDistance(position,pos)) {
				min = position;
			}
		}
		curPosition = min;
		return true;
	}
	
}
