
import java.util.ArrayList;

public class Archer extends Hero {
    public Archer(Position pos) {
        super(pos);
    }
    
    @Override
    void calMoveArea() {
        int x = getCurPosition().getX();
        int y = getCurPosition().getY();
        ArrayList<Position> mArea = new ArrayList<Position> ();
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                Position tmp = new Position(x+i, y+j);
                if(tmp.valid()) mArea.add(tmp);
            }
        }
        setMoveArea(mArea);
    }

    @Override
    void calAttackArea() {
        int x = getCurPosition().getX();
        int y = getCurPosition().getY();
        ArrayList<Position> aArea = new ArrayList<Position> ();
        Position tmp;
        for(int i = 0; i < Config.GAME_WIDTH; i++) {
            tmp = new Position(i, y);
            aArea.add(tmp);
        }
        for(int i = 0; i < Config.GAME_HEIGHT; i++) {
            tmp = new Position(x, i);
            aArea.add(tmp);
        }
        aArea.remove(new Position(x, y));
        aArea.remove(new Position(x, y));
        setAttackArea(aArea);
    }

    @Override
    boolean move(Position pos, ArrayList<Hero> heroes) {
        for(Position p: getMoveArea(heroes)) {
            if (pos.equals(p)) {
            	setCurPosition(pos);
                return true;
            }
        }
        return false;
    }

    @Override
    boolean attack(Position pos) {
        for(Position p: getAttackArea()) {
            if (pos.equals(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    ArrayList<Position> calDamageArea(Position pos) {
        ArrayList<Position> damageArea = new ArrayList<Position>();
        
        damageArea.add(pos);
        
        return damageArea;
    }
}

