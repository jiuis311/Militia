import java.util.ArrayList;


public class Minion extends Monster {
	Minion(Position pos) {
		super(pos);
		this.setMonsterImage(new DrawTile("/monster2.png"));
	}

	@Override
	public void calMoveArea(ArrayList<Monster> monsters) {
		ArrayList<Position> mArea = new ArrayList<Position>();
		Position tmp = new Position(getCurPosition().getX()+1,getCurPosition().getY());
		if (tmp.valid()) mArea.add(tmp);
		tmp = new Position(getCurPosition().getX(),getCurPosition().getY());
		mArea.add(tmp);
		tmp = new Position(getCurPosition().getX()-1,getCurPosition().getY());
		if (tmp.valid()) mArea.add(tmp);
		tmp = new Position(getCurPosition().getX(),getCurPosition().getY()+1);
		if (tmp.valid()) mArea.add(tmp);
		tmp = new Position(getCurPosition().getX(),getCurPosition().getY()-1);
		if (tmp.valid()) mArea.add(tmp);
		for(Monster mons: monsters) {
			Position pos = mons.getCurPosition();
			mArea.remove(pos);
		}
		setMoveArea(mArea);
	}
	
	@Override
	public boolean move(ArrayList<Hero> heros, ArrayList<Monster> mons) {
		calMoveArea(mons);
		Position monPos = getCurPosition();
		Position min = heros.get(0).getCurPosition();
		for (Hero hero: heros) {
			Position heroPos = hero.getCurPosition();
			if (min.getDistance(monPos) > heroPos.getDistance(monPos)) {
				min = heroPos;
			}
		}
		Position min2 = getCurPosition();
		for (Position position: getMoveArea()) {
			if (min2.getDistance(min) > position.getDistance(min)) {
				min2 = position;
			}
		}
		setCurPosition(min2);
		return true;
	}

	
}
