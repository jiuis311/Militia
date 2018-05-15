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
		int x = getCurPosition().getX();
        int y = getCurPosition().getY();
		ArrayList<Position> mArea = new ArrayList<Position>();	
		for(int i = -2; i <= 2; i++) {
            Position tmp_1 = new Position(x+i, y-i);
            if (tmp_1.valid()) mArea.add(tmp_1);
            Position tmp_2 = new Position(x+i, y+i);
            if (tmp_2.valid()) mArea.add(tmp_2);
        }
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
