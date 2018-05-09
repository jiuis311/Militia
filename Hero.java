import java.util.ArrayList;

abstract class Hero extends Character{
    private ArrayList<Position> attackArea;
    private State state;

    public void setAttackArea(ArrayList<Position> attackArea) {
            this.attackArea = attackArea;
    }

    //
    public enum State {
        UNSELECT,
        SELECTING,
        MOVING,
        MOVED,
        ATTACKING,
        DONE
    }
    
    Hero(Position position) {
        super(position);
        attackArea = new ArrayList<Position> ();
        state = State.UNSELECT;
    }
    
    public void setState(State state) { this.state = state; }
    public State getState() { return state; }
    
    void getDetail() {
        System.out.println("Vi tri di chuyen");
        for(Position pos: getMoveArea()) {
            System.out.print('('+Integer.toString(pos.getX())+','+pos.getY()+')');
        }
        System.out.println();
        System.out.println("Vi tri tan cong");
        for(Position pos: getAttackArea()) {
            System.out.print('('+Integer.toString(pos.getX())+','+pos.getY()+')');
        }
    }
    
    public ArrayList<Position> getMoveArea(ArrayList<Hero> heroes) {
       calMoveArea();
       blockMoveArea(heroes);
       return moveArea;
    }
    
    public ArrayList<Position> getAttackArea() {
        calAttackArea();
        return attackArea;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Hero) 
            return curPosition.equals(((Hero) o).curPosition);
        return false;
    }
    
    private void blockMoveArea(ArrayList<Hero> heroes) {
        Position position = null;
        
        for(Hero hero: heroes) {
            position = hero.getCurPosition();
            if(!this.equals(hero))
                moveArea.remove(position);
        }
    }
    
    abstract void calMoveArea();
    abstract void calAttackArea();
    abstract boolean move(Position pos, ArrayList<Hero> heroes);
    abstract boolean attack(Position pos);
    abstract ArrayList<Position> calDamageArea(Position pos);
}
