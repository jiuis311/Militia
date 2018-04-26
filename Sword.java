
import java.util.ArrayList;

public class Sword extends Hero {
		
    public Sword(Position pos) {
        super(pos);
        this.setHeroImage(new DrawTile("/short_sword.png"));
    }
    
    @Override
    void calMoveArea() {
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
    void calAttackArea() {
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
    boolean move(Position pos) {
        calMoveArea();
        for(Position p: getMoveArea()) {
            if (pos.equals(p)) {
                setCurPosition(pos);
                return true;
            }
        }
        return false;
    }

    @Override
    boolean attack(Position pos) {
        calAttackArea();
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
