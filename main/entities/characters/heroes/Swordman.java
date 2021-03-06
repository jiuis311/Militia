package main.entities.characters.heroes;

import java.util.ArrayList;

import main.helpers.Position;

public class Swordman extends Hero {
		
    public Swordman(Position pos) {
        super(pos);
    }
    
    @Override
    public void calMoveArea() {
        int x = getCurPosition().getX();
        int y = getCurPosition().getY();
        ArrayList<Position> mArea = new ArrayList<Position> ();
        for(int i = -2; i <= 2; i++) {
            for(int j = -2; j <= 2; j++) {
                Position tmp = new Position(x+i, y+j);
                if(tmp.valid()) mArea.add(tmp);
            }
        }
        setMoveArea(mArea);
    }

    @Override
    public void calAttackArea() {
        int x = getCurPosition().getX();
        int y = getCurPosition().getY();
        ArrayList<Position> aArea = new ArrayList<Position> ();
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                Position tmp = new Position(x+i, y+j);
                if(tmp.valid()) aArea.add(tmp);
            }
        }
        aArea.remove(new Position(x, y));
        setAttackArea(aArea);
    }

    @Override
    public boolean move(Position pos, ArrayList<Hero> heroes) {
        for(Position p: getMoveArea(heroes)) {
            if (pos.equals(p)) {
                setCurPosition(pos);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean attack(Position pos) {
        for(Position p: getAttackArea()) {
            if (pos.equals(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Position> calDamageArea(Position pos) {
        ArrayList<Position> damageArea = new ArrayList<Position>();
        
        damageArea.add(pos);
        
        return damageArea;
    }
}
