import java.util.ArrayList;

abstract class Character extends Entity {
    private ArrayList<Position> moveArea;
    
    public ArrayList<Position> getMoveArea(ArrayList<?> heroes, ArrayList<?> monsters) {
        calMoveArea(heroes, monsters);
        return moveArea;
    }
    abstract void calMoveArea(ArrayList<?> heroes, ArrayList<?> monsters);
}
