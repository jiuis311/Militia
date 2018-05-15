package main.entities.characters.heroes;
import java.util.ArrayList;

import main.entities.characters.Character;
import main.helpers.Position;

public abstract class Hero extends Character{
    private ArrayList<Position> attackArea;
    private State state;
    private boolean shield;
    
    public void setShield(boolean shield) { this.shield = shield; }
    public boolean getShield() { return shield; }
    
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
        shield = false;
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
            return this.getCurPosition().equals(((Hero) o).getCurPosition());
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
    
    public abstract void calMoveArea();
    public abstract void calAttackArea();
    public abstract boolean move(Position pos, ArrayList<Hero> heroes);
    public abstract boolean attack(Position pos);
    public abstract ArrayList<Position> calDamageArea(Position pos);
}
