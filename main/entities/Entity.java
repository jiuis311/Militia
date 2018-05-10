package main.entities;
import main.helpers.Position;

public abstract class Entity {
    protected Position curPosition;
    
    public Position getCurPosition() { return curPosition; }
    public void setCurPosition(Position position) { curPosition = position; }
    
    protected Entity(Position position) {
        setCurPosition(position);
    }
}
