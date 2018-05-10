package main.entities.items;

import java.util.ArrayList;
import main.helpers.Position;

public class Bomb extends Item {
    private ArrayList<Position> damageArea;
    
    public Bomb(Position position) {
        super(position);
        damageArea = new ArrayList<Position>();
        
        int x = getCurPosition().getX();
        int y = getCurPosition().getY();
        
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                Position tmp = new Position(x+i, y+j);
                if(tmp.valid()) damageArea.add(tmp);
            }
        }
    }
    
    public ArrayList<Position> getDamageArea() { return damageArea; }
}
