abstract class Entity {
    protected Position curPosition;
    
    public Position getCurPosition() { return curPosition; }
    public void setCurPosition(Position position) { curPosition = position; }
    
    Entity(Position position) {
        setCurPosition(position);
    }
}
