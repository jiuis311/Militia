package main.entities.characters;
import java.util.ArrayList;

import main.entities.Entity;
import main.helpers.Position;

public abstract class Character extends Entity {
    protected ArrayList<Position> moveArea;
    
    public ArrayList<Position> getMoveArea() {
        return moveArea;
    }
    
    public void setMoveArea(ArrayList<Position> moveArea) {
        this.moveArea = moveArea;
    }
    
    protected Character(Position position) {
        super(position);
        moveArea = new ArrayList<Position>();
    }
}
