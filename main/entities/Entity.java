package main.entities;
import main.helpers.Position;

public abstract class Entity {
    private Position curPosition;
    
    public Position getCurPosition() { return curPosition; }
    public void setCurPosition(Position position) { curPosition = position; }
    
    protected Entity(Position position) {
        setCurPosition(position);
    }
}
