
import java.util.ArrayList;

public class Lancer extends Hero {
		
    public Lancer(Position pos) {
        super(pos);
        this.setHeroImage(new DrawTile("/spear3.png"));
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
        for(int i = -2; i <= 2; i++) {
            Position tmp_1 = new Position(x+i, y-i);
            if (tmp_1.valid()) aArea.add(tmp_1);
            Position tmp_2 = new Position(x+i, y+i);
            if (tmp_2.valid()) aArea.add(tmp_2);
        }
        aArea.remove(new Position(x, y));
        aArea.remove(new Position(x, y));
        setAttackArea(aArea);
    }

    @Override
    boolean move(Position pos) {
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
        
        int x = getCurPosition().getX(), y = getCurPosition().getY();
        int sub_x = pos.getX() - x, sub_y = pos.getY() - y;
        sub_x = (sub_x % 2 == 0) ? sub_x/2 : sub_x*2;
        sub_y = (sub_y % 2 == 0) ? sub_y/2 : sub_y*2;
        
        damageArea.add(new Position(x + sub_x, y + sub_y));
        
        return damageArea;
    }
}

