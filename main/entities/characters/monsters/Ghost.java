package main.entities.characters.monsters;
import java.util.ArrayList;

import main.entities.characters.heroes.Hero;
import main.helpers.Position;

public class Ghost extends Monster{
	private boolean cloak;
	public boolean getCloak() { return cloak; }
	public void setCloak() {
		cloak = !cloak;
	}
	public Ghost(Position pos) {
		super(pos);
		cloak = false;
	}
	@Override
	void calMoveArea(ArrayList<Monster> monsters) {
		ArrayList<Position> mArea = new ArrayList<Position>();
		Position tmp = new Position(getCurPosition().getX()+2,getCurPosition().getY()+2);
		if (tmp.valid()) mArea.add(tmp);
		tmp = new Position(getCurPosition().getX(),getCurPosition().getY());
		this.getMoveArea().add(tmp);
		tmp = new Position(getCurPosition().getX()+2,getCurPosition().getY()-2);
		if (tmp.valid()) mArea.add(tmp);
		tmp = new Position(getCurPosition().getX()-2,getCurPosition().getY()+2);
		if (tmp.valid()) mArea.add(tmp);
		tmp = new Position(getCurPosition().getX()-2,getCurPosition().getY()-2);
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
		setCloak();
		return true;
	}
	
}
