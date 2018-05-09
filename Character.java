import java.util.ArrayList;

abstract class Character extends Entity {
    protected ArrayList<Position> moveArea;
    
    public void setMoveArea(ArrayList<Position> moveArea) {
        this.moveArea = moveArea;
    }
    
    Character(Position position) {
        super(position);
        moveArea = new ArrayList<Position>();
    }
}
