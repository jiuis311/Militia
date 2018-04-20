import java.util.ArrayList;


public class Minion extends Monster {
	Minion(Position pos) {
		super(pos);
	}

	@Override
	public void calculateMoveArea(ArrayList<Monster> monsters) {
		moveArea.clear();
		Position tmp = new Position(curPosition.getX()+1,curPosition.getY());
		if (tmp.getX() < Config.GAME_WIDTH) this.moveArea.add(tmp);
		tmp = new Position(curPosition.getX(),curPosition.getY());
		this.moveArea.add(tmp);
		tmp = new Position(curPosition.getX()-1,curPosition.getY());
		if (tmp.getX() >= 0) this.moveArea.add(tmp);
		tmp = new Position(curPosition.getX(),curPosition.getY()+1);
		if (tmp.getY() <= Config.GAME_HEIGHT) this.moveArea.add(tmp);
		tmp = new Position(curPosition.getX(),curPosition.getY()-1);
		if (tmp.getY() >= 0) this.moveArea.add(tmp);
		for(Monster mons: monsters) {
			Position pos = mons.curPosition;
			moveArea.remove(pos);
		}
	}
	
	@Override
	public boolean move(Position pos, ArrayList<Monster> mons) {
		calculateMoveArea(mons);
		Position min = curPosition;
		for (Position position: moveArea) {
			if (min.getDistance(pos) > position.getDistance(pos)) {
				min = position;
			}
		}
		curPosition = min;
		return true;
	}
	
}
