public class Sword extends Hero {
    public Sword(Position pos) {
        super(pos);
    }
    
    @Override
    void calMoveArea() {
        int x = curPosition.getX();
        int y = curPosition.getY();
        moveArea.clear();
        for(int i = -2; i <= 2; i++) {
            for(int j = -2; j <= 2; j++) {
                Position tmp = new Position(x+i, y+j);
                if(tmp.valid()) moveArea.add(tmp);
            }
        }
    }

    @Override
    void calAttackArea() {
        int x = curPosition.getX();
        int y = curPosition.getY();
        attackArea.clear();
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                Position tmp = new Position(x+i, y+j);
                if(tmp.valid()) attackArea.add(tmp);
            }
        }
        attackArea.remove(new Position(x, y));
    }

    @Override
    boolean move(Position pos) {
        calMoveArea();
        for(Position p: moveArea) {
            if (pos.equals(p)) {
                curPosition.setX(pos.getX());
                curPosition.setY(pos.getY());
                return true;
            }
        }
        return false;
    }

    @Override
    boolean attack(Position pos) {
        calAttackArea();
        for(Position p: attackArea) {
            if (pos.equals(p)) {
                return true;
            }
        }
        return false;
    }
}